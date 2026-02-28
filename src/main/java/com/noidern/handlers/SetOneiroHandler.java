package com.noidern.handlers;

import com.noidern.components.PlayerOneiroComponent;
import com.noidern.events.ModifyOneiroEvent;
import com.noidern.events.SetOneiroEvent;

import java.util.function.Consumer;

public class SetOneiroHandler implements Consumer<SetOneiroEvent>
{
    @Override
    public void accept(SetOneiroEvent _event)
    {
        if (!_event.playerRef().isValid())
            return;

        var _store = _event.playerRef().getStore();
        var _oneiro = _store.getComponent(_event.playerRef(), PlayerOneiroComponent.getComponentType());

        if (_oneiro == null)
            return;

        _oneiro.setOneiro(_event.amount());
        ModifyOneiroEvent.dispatch(_event.playerRef());
    }
}
