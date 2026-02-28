package com.noidern.systems;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.server.core.asset.type.blocktick.BlockTickStrategy;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.chunk.BlockComponentChunk;
import com.hypixel.hytale.server.core.universe.world.chunk.section.BlockSection;
import com.hypixel.hytale.server.core.universe.world.chunk.section.ChunkSection;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.noidern.components.MorpheusBloomComponent;
import com.noidern.components.PlayerOneiroComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class MorpheusBloomSystem extends EntityTickingSystem<ChunkStore>
{
    @Override
    public void tick(float dt, int index, @NonNull ArchetypeChunk<ChunkStore> archetypeChunk, @NonNull Store<ChunkStore> store, @NonNull CommandBuffer<ChunkStore> commandBuffer)
    {
        BlockSection _blocks = archetypeChunk.getComponent(index, BlockSection.getComponentType());
        assert _blocks != null;

        if (_blocks.getTickingBlocksCountCopy() != 0)
        {
            ChunkSection _section = archetypeChunk.getComponent(index, ChunkSection.getComponentType());
            assert _section != null;
            BlockComponentChunk _blockComponentChunk = commandBuffer.getComponent(_section.getChunkColumnReference(), BlockComponentChunk.getComponentType());
            assert _blockComponentChunk != null;

            _blocks.forEachTicking(_blockComponentChunk, commandBuffer, _section.getY(), (_blockComponentChunk1, _commandBuffer1, _localX, _localY, _localZ, _blockId) ->
            {
                Ref<ChunkStore> _blockRef = _blockComponentChunk.getEntityReference(ChunkUtil.indexBlockInColumn(_localX, _localY, _localZ));

                if (_blockRef == null)
                    return BlockTickStrategy.IGNORED;
                else
                {
                    MorpheusBloomComponent _object = _commandBuffer1.getComponent(_blockRef, MorpheusBloomComponent.getComponentType());

                    if (_object != null)
                    {
                        var _player = Universe.get().getPlayer(_object.getPlayerUUID());

                        if (_player == null)
                            return BlockTickStrategy.CONTINUE;

                        var _store = _player.getHolder();
                        var _oneiro = _store.getComponent(PlayerOneiroComponent.getComponentType());
                        _object.giveOneiro(_oneiro, dt);

                        return BlockTickStrategy.CONTINUE;
                    }
                    else
                        return BlockTickStrategy.IGNORED;
                }
            });
        }
     }

    @Override
    public @Nullable Query<ChunkStore> getQuery() {
        return Query.and(BlockSection.getComponentType(), ChunkSection.getComponentType());
    }
}
