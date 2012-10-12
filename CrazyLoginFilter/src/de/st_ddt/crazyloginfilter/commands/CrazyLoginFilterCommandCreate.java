package de.st_ddt.crazyloginfilter.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.st_ddt.crazyloginfilter.CrazyLoginFilter;
import de.st_ddt.crazyloginfilter.data.PlayerAccessFilter;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandExecutorException;
import de.st_ddt.crazyplugin.exceptions.CrazyException;
import de.st_ddt.crazyutil.locales.Localized;

public class CrazyLoginFilterCommandCreate extends CrazyLoginFilterCommandExecutor
{

	public CrazyLoginFilterCommandCreate(CrazyLoginFilter plugin)
	{
		super(plugin);
	}

	@Override
	@Localized("CRAZYLOGINFILTER.COMMAND.CREATED $Name$")
	public void command(CommandSender sender, String[] args) throws CrazyException
	{
		if (sender instanceof ConsoleCommandSender)
			throw new CrazyCommandExecutorException(false);
		final Player player = (Player) sender;
		final PlayerAccessFilter data = new PlayerAccessFilter(player);
		data.setCheckIP(false);
		data.setCheckConnection(false);
		plugin.getCrazyDatabase().save(data);
		plugin.sendLocaleMessage("COMMAND.CREATED", sender, player.getName());
	}
}
