package com.noidern.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.LearnItemEvent;

import javax.annotation.Nonnull;

public class MorpheusLearnCommand extends AbstractPlayerCommand
{
    private final RequiredArg<String> idArg;

    public MorpheusLearnCommand()
    {
        super("learn", "Learn an item");
        this.setPermissionGroup(GameMode.Creative);
        this.idArg = withRequiredArg("id", "Item id", ArgTypes.STRING);
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world)
    {
        var _id = idArg.get(context);

        if (store.getComponent(ref, PlayerOneiroComponent.getComponentType()) == null)
        {
            playerRef.sendMessage(Message.raw("No Oneiro data found"));
            return;
        }

        playerRef.sendMessage(Message.raw("You learned %s".formatted(_id)));
        LearnItemEvent.dispatch(ref, _id);
    }
}
