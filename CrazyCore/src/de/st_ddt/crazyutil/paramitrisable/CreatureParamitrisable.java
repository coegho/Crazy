package de.st_ddt.crazyutil.paramitrisable;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.EntityType;

import de.st_ddt.crazyplugin.exceptions.CrazyCommandNoSuchException;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandParameterException;
import de.st_ddt.crazyplugin.exceptions.CrazyException;

public class CreatureParamitrisable extends TypedParamitrisable<EntityType>
{

	public final static EntityType[] CREATURE_TYPES = getCreatureTypes();
	public final static String[] CREATURE_NAMES = getCreatureNames();

	private static EntityType[] getCreatureTypes()
	{
		final LinkedList<EntityType> res = new LinkedList<EntityType>();
		for (final EntityType type : EntityType.values())
			if (type.isAlive() && type.isSpawnable())
				res.add(type);
		return res.toArray(new EntityType[res.size()]);
	}

	private static String[] getCreatureNames()
	{
		final LinkedList<String> res = new LinkedList<String>();
		for (final EntityType type : EntityType.values())
			if (type.isAlive() && type.isSpawnable())
				res.add(type.toString());
		return res.toArray(new String[res.size()]);
	}

	public CreatureParamitrisable(final EntityType defaultValue)
	{
		super(defaultValue);
	}

	@Override
	public void setParameter(final String parameter) throws CrazyException
	{
		try
		{
			value = EntityType.valueOf(parameter.toUpperCase());
		}
		catch (final Exception e)
		{
			throw new CrazyCommandNoSuchException("CreatureType", parameter, CREATURE_NAMES);
		}
		finally
		{
			if (value != null)
				if (!value.isAlive() || !value.isSpawnable())
					throw new CrazyCommandParameterException(0, "CreatureType", CREATURE_NAMES);
		}
	}

	@Override
	public List<String> tab(String parameter)
	{
		parameter = parameter.toUpperCase();
		final List<String> res = new LinkedList<String>();
		for (final String name : CREATURE_NAMES)
			if (name.startsWith(parameter))
				res.add(name);
		return res;
	}
}
