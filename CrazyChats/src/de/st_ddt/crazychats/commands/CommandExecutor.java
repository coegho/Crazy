package de.st_ddt.crazychats.commands;

import de.st_ddt.crazychats.CrazyChats;
import de.st_ddt.crazychats.data.ChatPlayerData;
import de.st_ddt.crazyplugin.commands.CrazyPlayerDataPluginCommandExecutor;

abstract class CommandExecutor extends CrazyPlayerDataPluginCommandExecutor<ChatPlayerData, CrazyChats> implements CommandExecutorInterface
{

	CommandExecutor(final CrazyChats plugin)
	{
		super(plugin);
	}
}
