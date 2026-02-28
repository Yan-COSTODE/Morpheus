package com.noidern.commands;

import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.SetOneiroEvent;

import javax.annotation.Nonnull;

public class MorpheusSetOneiroCommand extends AbstractPlayerCommand
{
    private final DefaultArg<Integer> amountArg;

    public MorpheusSetOneiroCommand()
    {
        super("setOneiro", "Set your Oneiro");
        this.setPermissionGroup(GameMode.Creative);
        this.amountArg = withDefaultArg("amount", "Oneiro amount (>0)", ArgTypes.INTEGER, 100, "100")
                .addValidator(Validators.greaterThan(0));
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world)
    {
        var _amount = amountArg.get(context);

        if (store.getComponent(ref, PlayerOneiroComponent.getComponentType()) == null)
        {
            playerRef.sendMessage(Message.raw("No Oneiro data found"));
            return;
        }

        playerRef.sendMessage(Message.raw("You set your Oneiro to %d".formatted(_amount)));
        SetOneiroEvent.dispatch(ref, _amount);
    }
}
