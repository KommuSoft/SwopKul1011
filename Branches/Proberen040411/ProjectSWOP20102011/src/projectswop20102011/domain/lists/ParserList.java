package projectswop20102011.domain.lists;

import java.util.HashMap;
import projectswop20102011.utils.Parser;

/**
 * A list that contains parsers to parse data.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ParserList {

    /**
     * The internal list holding the parsers.
     */
    private HashMap<Class,Parser> parsers = new HashMap<Class,Parser>();


    /**
     * Search for a parser that can parse the given parsing type.
     * @param parsingType The given parsing type to parse.
     * @return A parser if this ParserList contains a parser that can parse, otherwise null.
     */
    public Parser findParserFromParsingType (Class parsingType) {
        return this.getParsers().get(parsingType);
    }
    /**
     * Returns the hashmap of parsers.
     * @return the hashmap of parsers.
     */
    private HashMap<Class,Parser> getParsers () {
        return this.parsers;
    }
    /**
     * Adds the given parser to the parser list.
     * @param parser The parser to add to the list.
     */
    public void addParser (Parser parser) {
        this.getParsers().put(parser.getParsingType(), parser);
    }

    /**
     * Removes the given parser from the parser list.
     * @param parser The parser to remove from the list.
     */
    public void removeParser (Parser parser) {
        this.getParsers().remove(parser.getParsingType());
    }

}
