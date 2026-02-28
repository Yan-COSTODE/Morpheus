package com.noidern.handlers;

import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.ModifyOneiroEvent;
import com.noidern.pages.MorpheusHUDPage;

import java.util.function.Consumer;

public class ModifyOneiroHandler implements Consumer<ModifyOneiroEvent>
{
    @Override
    public void accept(ModifyOneiroEvent _event)
    {
        if (!_event.playerRef().isValid())
            return;

        var _store = _event.playerRef().getStore();
        var _oneiro = _store.getComponent(_event.playerRef(), PlayerOneiroComponent.getComponentType());
        var _playerRef = _store.getComponent(_event.playerRef(), PlayerRef.getComponentType());

        if (_playerRef == null || _oneiro == null)
            return;

        var _player = _store.getComponent(_event.playerRef(), Player.getComponentType());

        if (_player == null)
            return;

        _player.getHudManager().setCustomHud(_playerRef, new MorpheusHUDPage(_playerRef));
    }
}