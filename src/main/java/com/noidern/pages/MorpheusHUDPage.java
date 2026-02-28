package com.noidern.pages;

import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.noidern.components.PlayerOneiroComponent;

import javax.annotation.Nonnull;

public class MorpheusHUDPage extends CustomUIHud
{
    private final PlayerRef playerRef;

    public MorpheusHUDPage(@Nonnull PlayerRef playerRef)
    {
        super(playerRef);
        this.playerRef = playerRef;
    }

    @Override
    public void build(UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.append("Pages/MorpheusHUD.ui");
        uiCommandBuilder.set("#OneiroLabel.Text", getOneiro());
    }

    private String getOneiro()
    {
        var _ref = playerRef.getReference();

        if (_ref == null)
            return "ONEIRO: Error";

        var _store = _ref.getStore();
        var _oneiro = _store.getComponent(_ref, PlayerOneiroComponent.getComponentType());

        if (_oneiro == null)
            return "ONEIRO: Error";

        return "ONEIRO: " + _oneiro.getOneiroDisplay();
    }
}
