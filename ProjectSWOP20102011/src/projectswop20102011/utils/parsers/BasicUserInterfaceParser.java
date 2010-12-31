package projectswop20102011.utils.parsers;

/**
 * An abstract class prividing simple functionalities for a basic UserInterfaceParser.
 * @param <T> The type of object the parser will parse.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class BasicUserInterfaceParser<T> extends BasicParser<T> implements UserInterfaceParser<T> {

    /**
     * A string containing the format information.
     */
    private final String formatInformation;

    /**
     * Creates a new instance of a BasicUserInterfaceParser with a given type of objects that will be parsed and a String containing information about the format.
     * @param parsingType The given type of objects that will be parsed.
     * @param formatInformation A string explaining the format of the parser.
     */
    protected BasicUserInterfaceParser (Class<T> parsingType, String formatInformation) {
        super(parsingType);
        this.formatInformation = formatInformation;
    }

    /**
     * Returns a String containing information about the format of the parser.
     * @return A String containing information about the format of the parser.
     */
    @Override
    public String getParserFormatInformation() {
        return this.formatInformation;
    }

}
