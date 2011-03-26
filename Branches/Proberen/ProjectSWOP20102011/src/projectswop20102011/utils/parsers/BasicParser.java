package projectswop20102011.utils.parsers;

import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.ObjectHolder;
import projectswop20102011.utils.Parser;

/**
 * An abstract class that provides functionality to save the class that will be parsed.
 * @author Willem Van Onsem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class BasicParser<T> implements Parser<T> {

    /**
     * A field that saves the type of objects that will be parsed.
     */
    private final Class<T> parsingType;

    /**
     * Creates a new BasicParser with a given class that specifies the type of objects that will be parsed.
     * @param parsingType The given type of objects that will be parsed.
     */
    protected BasicParser (Class<T> parsingType) {
        this.parsingType = parsingType;
    }

    /**
     * Parses the given textual representation to it's object equivalent.
     * @param textualRepresentation The textual representation to parse.
     * @return The object that is the equivalent of the given textual representation.
     * @throws ParsingException If the given textual representation can't be parsed.
     */
    @Override
    public T parse (String textualRepresentation) throws ParsingException {
        ObjectHolder<T> holder = new ObjectHolder<T>();
        this.parse(textualRepresentation, holder);
        return holder.getObject();
    }
    /**
     * Gets the type of objects that will be parsed by the parser.
     * @return The type of objects that will be parsed by the parser.
     */
    public Class<T> getParsingType () {
        return this.parsingType;
    }

}
