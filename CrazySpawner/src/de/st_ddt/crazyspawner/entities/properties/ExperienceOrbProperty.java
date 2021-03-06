package de.st_ddt.crazyspawner.entities.properties;

import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;

import de.st_ddt.crazyspawner.CrazySpawner;
import de.st_ddt.crazyutil.paramitrisable.IntegerParamitrisable;
import de.st_ddt.crazyutil.paramitrisable.Paramitrisable;
import de.st_ddt.crazyutil.paramitrisable.TabbedParamitrisable;
import de.st_ddt.crazyutil.source.Localized;

public class ExperienceOrbProperty extends BasicProperty
{

	protected final int minXP;
	protected final int maxXP;

	public ExperienceOrbProperty()
	{
		super();
		this.minXP = -1;
		this.maxXP = -1;
	}

	public ExperienceOrbProperty(final ConfigurationSection config)
	{
		super(config);
		final int minXP = config.getInt("minXP", -1);
		final int maxXP = config.getInt("maxXP", -1);
		this.minXP = Math.max(Math.min(minXP, maxXP), -1);
		this.maxXP = Math.max(Math.max(minXP, maxXP), -1);
	}

	public ExperienceOrbProperty(final Map<String, ? extends Paramitrisable> params)
	{
		super(params);
		final IntegerParamitrisable minXPParam = (IntegerParamitrisable) params.get("minxp");
		final IntegerParamitrisable maxXPParam = (IntegerParamitrisable) params.get("maxxp");
		this.minXP = Math.max(Math.min(minXPParam.getValue(), maxXPParam.getValue()), -1);
		this.maxXP = Math.max(Math.max(minXPParam.getValue(), maxXPParam.getValue()), -1);
	}

	@Override
	public void apply(final Entity entity)
	{
		final ExperienceOrb orb = (ExperienceOrb) entity;
		if (minXP != -1)
			orb.setExperience(getRandom(minXP, maxXP));
	}

	@Override
	public void getCommandParams(final Map<String, ? super TabbedParamitrisable> params, final CommandSender sender)
	{
		final IntegerParamitrisable minXPParam = new IntegerParamitrisable(minXP);
		params.put("minxp", minXPParam);
		final IntegerParamitrisable maxXPParam = new IntegerParamitrisable(maxXP);
		params.put("maxxp", maxXPParam);
	}

	@Override
	public void save(final ConfigurationSection config, final String path)
	{
		config.set(path + "minXP", minXP);
		config.set(path + "maxXP", maxXP);
	}

	@Override
	public void dummySave(final ConfigurationSection config, final String path)
	{
		config.set(path + "minXP", "int (-1 = default)");
		config.set(path + "maxXP", "int (-1 = default)");
	}

	@Override
	@Localized({ "CRAZYSPAWNER.ENTITY.PROPERTY.XP $MinXP$ $MaxXP$", "CRAZYSPAWNER.ENTITY.PROPERTY.XP.DEFAULT" })
	public void show(final CommandSender target)
	{
		if (minXP == -1)
			CrazySpawner.getPlugin().sendLocaleMessage("ENTITY.PROPERTY.XP.DEFAULT", target);
		else
			CrazySpawner.getPlugin().sendLocaleMessage("ENTITY.PROPERTY.XP", target, minXP, maxXP);
	}

	@Override
	public boolean equalsDefault()
	{
		return minXP == -1;
	}
}
