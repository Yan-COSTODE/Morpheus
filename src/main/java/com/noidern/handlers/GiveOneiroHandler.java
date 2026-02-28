package com.noidern.handlers;

import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.GiveOneiroEvent;
import com.noidern.events.ModifyOneiroEvent;

import java.util.function.Consumer;

public class GiveOneiroHandler implements Consumer<GiveOneiroEvent>
{
    @Override
    public void accept(GiveOneiroEvent _event)
    {
        if (!_event.playerRef().isValid())
            return;

        var _store = _event.playerRef().getStore();
        var _oneiro = _store.getComponent(_event.playerRef(), PlayerOneiroComponent.getComponentType());

        if (_oneiro == null)
            return;

        _oneiro.addOneiro(_event.amount());
        ModifyOneiroEvent.dispatch(_event.playerRef());
    }
}
