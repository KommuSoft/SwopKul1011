package projectswop20102011.factories;

import projectswop20102011.domain.MapItem;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public abstract class UnitFactory extends MapItemFactory {

	public UnitFactory(String s) throws InvalidMapItemTypeNameException {
		super(s);
	}

	@Override
	public abstract MapItem createMapItem(Object[] parameters);

}
