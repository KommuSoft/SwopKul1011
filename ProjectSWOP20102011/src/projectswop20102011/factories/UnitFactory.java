package projectswop20102011.factories;

import projectswop20102011.domain.MapItem;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.utils.TextScanner;

public abstract class UnitFactory extends MapItemFactory {

	public UnitFactory(String s) throws InvalidMapItemTypeNameException {
		super(s);
	}

	@Override
	public abstract MapItem createMapItem(String s);

	protected Long parseSpeed(String s) {
		TextScanner ts = new TextScanner(s);
		return ts.read(new SpeedParser());
	}
}
