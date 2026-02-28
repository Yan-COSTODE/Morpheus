package com.noidern.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.event.IEvent;
import com.hypixel.hytale.event.IEventDispatcher;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public record ModifyOneiroEvent(
        @Nonnull Ref<EntityStore> playerRef
) implements IEvent<Void>
{
    public static void dispatch(Ref<EntityStore> _playerRef)
    {
        IEventDispatcher<ModifyOneiroEvent, ModifyOneiroEvent> dispatcher = HytaleServer.get().getEventBus().dispatchFor(ModifyOneiroEvent.class);

        if (dispatcher.hasListener())
            dispatcher.dispatch(new ModifyOneiroEvent(_playerRef));
    }
}
