package com.noidern.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.ModifyOneiroEvent;

import javax.annotation.Nonnull;

public class MorpheusDebugCommand extends AbstractPlayerCommand
{
    public MorpheusDebugCommand()
    {
        super("debug", "Debug");
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world)
    {
        var _oneiro = store.getComponent(ref, PlayerOneiroComponent.getComponentType());

        if (_oneiro == null)
        {
            playerRef.sendMessage(Message.raw("No Oneiro data found"));
            return;
        }

        playerRef.sendMessage(Message.raw("==== Oneiro Debug ===="));
        playerRef.sendMessage(Message.raw("Oneiro: %d".formatted(_oneiro.getOneiro())));
        playerRef.sendMessage(Message.raw("Items: %d".formatted(_oneiro.getItems().size())));
    }
}
