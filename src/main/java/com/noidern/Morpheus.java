package com.noidern;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import com.noidern.commands.MorpheusCommand;
import com.noidern.components.MorpheusBloomComponent;
import com.noidern.components.PlayerOneiroComponent;
import com.noidern.oneiro.OneiroTable;
import com.noidern.events.*;
import com.noidern.handlers.*;
import com.noidern.systems.MorpheusBloomInitializer;
import com.noidern.systems.MorpheusBloomSystem;
import com.noidern.systems.PlayerJoinSystem;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class Morpheus extends JavaPlugin
{
    private final Config<MorpheusConfig> config = this.withConfig("MorpheusConfig", MorpheusConfig.CODEC);

    public Morpheus(@NonNullDecl JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        OneiroTable.readConfig(config);

        registerChunkStoreComponent();
        registerChunkStoreSystem();
        registerEntityStoreComponent();
        registerEntityStoreSystem();
        registerEvent();
        registerCommand();
    }

    private void registerChunkStoreComponent()
    {
        var _registry = getChunkStoreRegistry();
        MorpheusBloomComponent.setComponentType(_registry.registerComponent(MorpheusBloomComponent.class, "MorpheusBloom_BlockData", MorpheusBloomComponent.CODEC));
    }

    private void registerChunkStoreSystem()
    {
        var _registry = getChunkStoreRegistry();
        _registry.registerSystem(new MorpheusBloomInitializer());
        _registry.registerSystem(new MorpheusBloomSystem());
    }

    private void registerEntityStoreComponent()
    {
        var _registry = getEntityStoreRegistry();
        PlayerOneiroComponent.setComponentType(_registry.registerComponent(PlayerOneiroComponent.class, "Oneiro_PlayerData", PlayerOneiroComponent.CODEC));
    }

    private void registerEntityStoreSystem()
    {
        var _registry = getEntityStoreRegistry();
        _registry.registerSystem(new PlayerJoinSystem());
    }

    private void registerEvent()
    {
        var _registry = getEventRegistry();
        _registry.register(GiveOneiroEvent.class, new GiveOneiroHandler());
        _registry.register(ModifyOneiroEvent.class, new ModifyOneiroHandler());
        _registry.register(SetOneiroEvent.class, new SetOneiroHandler());
        _registry.register(LearnItemEvent.class, new LearnItemHandler());
        _registry.register(UnlearnItemEvent.class, new UnlearnItemHandler());
    }

    private void registerCommand()
    {
        var _registry = getCommandRegistry();
        _registry.registerCommand(new MorpheusCommand());
    }

    @Override
    protected void shutdown() {
        OneiroTable.writeConfig();
    }
}
