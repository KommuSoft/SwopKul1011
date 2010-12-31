package projectswop20102011.utils.parsers;

import projectswop20102011.utils.Parser;

/**
 * An interface that extends the Parser interface with information about the format of the parser (used to help users in the user interface).
 * @param <T> The type the parser will parse.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface UserInterfaceParser<T> extends Parser<T> {

    /**
     * Get infromation about the format the parser can parse.
     * @return Information about the format the parser can parse.
     */
    public abstract String getParserFormatInformation ();

}
