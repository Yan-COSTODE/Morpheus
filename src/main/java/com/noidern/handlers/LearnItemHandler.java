package com.noidern.handlers;

import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.LearnItemEvent;

import java.util.function.Consumer;

public class LearnItemHandler implements Consumer<LearnItemEvent> {

    @Override
    public void accept(LearnItemEvent _event) {
        if (!_event.playerRef().isValid())
            return;

        var _store = _event.playerRef().getStore();
        var _oneiro = _store.getComponent(_event.playerRef(), PlayerOneiroComponent.getComponentType());

        if (_oneiro == null)
            return;

        if (_event.id().equals("all"))
        {
            var _items = Item.getAssetMap().getAssetMap();

            for (var entry : _items.entrySet())
                _oneiro.learnItem(entry.getValue().getId());
        }
        else
        {
            if (Item.getAssetMap().getAsset(_event.id()) != null)
                _oneiro.learnItem(_event.id());
        }
    }
}
