package projectswop20102011.utils.parsers;

import projectswop20102011.utils.Parser;

/**
 * A parser that can parse enumeration values with a given enumeration.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class EnumParser<T> extends Parser<T> {

    public EnumParser (Class<? extends Enum>) {
        super(makeRegex(enumeration));
    }

    private static String makeRegex (Enum enumeration) {
    }

}