package com.noidern.systems;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefSystem;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.server.core.modules.block.BlockModule;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import com.noidern.components.MorpheusBloomComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class MorpheusBloomInitializer extends RefSystem<ChunkStore>
{
    @Override
    public void onEntityAdded(@NonNull Ref<ChunkStore> ref, @NonNull AddReason addReason, @NonNull Store<ChunkStore> store, @NonNull CommandBuffer<ChunkStore> commandBuffer) {
        BlockModule.BlockStateInfo _info = commandBuffer.getComponent(ref, BlockModule.BlockStateInfo.getComponentType());

        if (_info == null)
            return;

        MorpheusBloomComponent _object = commandBuffer.getComponent(ref, MorpheusBloomComponent.getComponentType());

        if (_object != null)
        {
            int x = ChunkUtil.xFromBlockInColumn(_info.getIndex());
            int y = ChunkUtil.yFromBlockInColumn(_info.getIndex());
            int z = ChunkUtil.zFromBlockInColumn(_info.getIndex());
            WorldChunk worldChunk = commandBuffer.getComponent(_info.getChunkRef(), WorldChunk.getComponentType());

            if (worldChunk != null)
                worldChunk.setTicking(x, y, z, true);
        }
    }

    @Override
    public void onEntityRemove(@NonNull Ref<ChunkStore> ref, @NonNull RemoveReason removeReason, @NonNull Store<ChunkStore> store, @NonNull CommandBuffer<ChunkStore> commandBuffer) {
        BlockModule.BlockStateInfo _info = commandBuffer.getComponent(ref, BlockModule.BlockStateInfo.getComponentType());

        if (_info == null)
            return;

        MorpheusBloomComponent _object = commandBuffer.getComponent(ref, MorpheusBloomComponent.getComponentType());

        if (_object != null)
        {
            int x = ChunkUtil.xFromBlockInColumn(_info.getIndex());
            int y = ChunkUtil.yFromBlockInColumn(_info.getIndex());
            int z = ChunkUtil.zFromBlockInColumn(_info.getIndex());
            WorldChunk worldChunk = commandBuffer.getComponent(_info.getChunkRef(), WorldChunk.getComponentType());

            if (worldChunk != null)
                worldChunk.setTicking(x, y, z, false);
        }
    }

    @Override
    public @Nullable Query<ChunkStore> getQuery() {
        return MorpheusBloomComponent.getComponentType();
    }
}
