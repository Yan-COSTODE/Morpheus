package com.noidern.handlers;

import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.UnlearnItemEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class UnlearnItemHandler implements Consumer<UnlearnItemEvent> {

    @Override
    public void accept(UnlearnItemEvent _event) {
        if (!_event.playerRef().isValid())
            return;

        var _store = _event.playerRef().getStore();
        var _oneiro = _store.getComponent(_event.playerRef(), PlayerOneiroComponent.getComponentType());

        if (_oneiro == null)
            return;

        if (_event.id().equals("all"))
        {
            _oneiro.setItems(new ArrayList<>());
        }
        else
        {
            if (Item.getAssetMap().getAsset(_event.id()) != null)
                _oneiro.unlearnItem(_event.id());
        }
    }
}

