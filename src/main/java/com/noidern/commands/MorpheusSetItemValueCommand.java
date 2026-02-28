package com.noidern.commands;

import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.noidern.oneiro.OneiroTable;

import javax.annotation.Nonnull;

public class MorpheusSetItemValueCommand extends AbstractPlayerCommand
{
    private final OptionalArg<String> idArg;
    private final RequiredArg<Integer> amountArg;

    public MorpheusSetItemValueCommand()
    {
        super("setItemValue", "Set an item Oneiro value");
        this.setPermissionGroup(GameMode.Creative);
        this.idArg = withOptionalArg("id", "Item id", ArgTypes.STRING);
        this.amountArg = withRequiredArg("amount", "Oneiro amount (>0)", ArgTypes.INTEGER)
                .addValidator(Validators.greaterThan(0));
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world)
    {
        String _id = idArg.get(context);
        var _amount = amountArg.get(context);

        if (_id == null || _id.isEmpty() || Item.getAssetMap().getAsset(_id) == null)
        {
            var _player = store.getComponent(ref, Player.getComponentType());

            if (_player != null)
            {
                var _itemInHand = _player.getInventory().getActiveHotbarItem();

                if (_itemInHand != null)
                {
                    var _handId = _itemInHand.getItemId();
                    playerRef.sendMessage(Message.raw("%s now have a value of %d Oneiro".formatted(_handId, _amount)));
                    OneiroTable.setOneiroValue(_handId, _amount);
                }

                return;
            }

            playerRef.sendMessage(Message.raw("No Item %s was found".formatted(_id)));
            return;
        }

        if (_amount == null)
            return;

        playerRef.sendMessage(Message.raw("%s now have a value of %d Oneiro".formatted(_id, _amount)));
        OneiroTable.setOneiroValue(_id, _amount);
    }
}
