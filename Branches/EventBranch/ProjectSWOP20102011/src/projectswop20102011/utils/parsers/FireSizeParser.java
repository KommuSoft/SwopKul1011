package projectswop20102011.utils.parsers;

import projectswop20102011.domain.FireSize;

/**
 * A parsers that can parse a textual representation to it's equivalent
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireSizeParser extends EnumParser<FireSize> {

    /**
     * Creates a new fire size parser.
     */
    public FireSizeParser () {
        super(FireSize.class,"format: local/house/facility");
    }

}
