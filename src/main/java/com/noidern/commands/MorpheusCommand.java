package com.noidern.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class MorpheusCommand extends AbstractCommandCollection
{
    public MorpheusCommand()
    {
        super("morpheus", "Morpheus Commands");
        addSubCommand(new MorpheusAddOneiroCommand());
        addSubCommand(new MorpheusSetOneiroCommand());
        addSubCommand(new MorpheusLearnCommand());
        addSubCommand(new MorpheusUnlearnCommand());
        addSubCommand(new MorpheusDebugCommand());
        addSubCommand(new MorpheusSetItemValueCommand());
    }
}
