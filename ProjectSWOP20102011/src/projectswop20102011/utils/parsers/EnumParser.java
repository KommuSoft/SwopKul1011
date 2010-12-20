package projectswop20102011.utils.parsers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.ObjectHolder;
import projectswop20102011.utils.Parser;

/**
 * A parser that can parse enumeration values with a given enumeration.
 * @param <T> The type of enumeration that will be parsed by this parser.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EnumParser<T extends Enum> implements Parser<T> {

    private final Hashtable<String,T> enumerationDictionary;
    private final Pattern searchPattern;

    /**
     * Creates a new instance of an enum parser with the given class object of the enumeration.
     * @param enumClass The class of the enumeration.
     */
    public EnumParser(Class<T> enumClass) {
        this.enumerationDictionary = buildEnumerationDictionary(enumClass);
        this.searchPattern = generatePattern();
    }

    /**
     * Generates a hastable based on the values of the enumeration of the given class.
     * @param <T> The given class of the enumeration.
     * @param enumClass The given class of the enumeration.
     * @return A hashtable containing a textual representation of the values of the enumeration as keys and their enumeration counterpart.
     */
    private static <T extends Enum> Hashtable<String,T> buildEnumerationDictionary(Class<T> enumClass) {
        try {
            Hashtable<String,T> dictionary = new Hashtable<String,T>();
            Method m = enumClass.getMethod("values", new Class[0]);
            T[] elements = (T[]) m.invoke(null, new Object[0]);
            for(T item : elements) {
                dictionary.put(item.toString().toLowerCase(), item);
            }
            return dictionary;
        } catch (NoSuchMethodException ex) {
            //We assume this can't happen
            Logger.getLogger(EnumParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            //We assume this can't happen
            Logger.getLogger(EnumParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //We assume this can't happen
            Logger.getLogger(EnumParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            //We assume this can't happen
            Logger.getLogger(EnumParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            //We assume this can't happen
            Logger.getLogger(EnumParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //We assume we will never return a null-pointer
        return null;
    }
    /**
     * A private method generating a regex pattern that can search for values of the enumeration.
     * @return A regex pattern that can search for values of the enumeration.
     */
    private Pattern generatePattern () {
        StringBuilder sbRegex = new StringBuilder();
        Iterator<String> iterator = this.getEnumerationDictionary().keySet().iterator();
        if(iterator.hasNext()) {
            sbRegex.append(iterator.next());
            while(iterator.hasNext()) {
                sbRegex.append(String.format("|%s",iterator.next()));
            }
        }
        return Pattern.compile(sbRegex.toString());
    }

    /**
     * Tests if there are instances that can be parsed in the given textual representation.
     * @param textualRepresentation A text to test for.
     * @return True if there are values in the textual representation that can be parsed, otherwise false.
     */
    @Override
    public boolean canParse(String textualRepresentation) {
        return this.getSearchPattern().matcher(textualRepresentation).find();
    }

    /**
     * Parse a textual representation to an instance of the specified enumeration.
     * @param textualRepresentation A textual representation to parse.
     * @param objectHolder A holder to transport the object out of the method.
     * @return The number of consumed characters of the textual representation.
     * @throws ParsingException If the given textual representation does not contain a textual representation of a value of the specified enumeration.
     */
    @Override
    public int parse(String textualRepresentation, ObjectHolder<? super T> objectHolder) throws ParsingException {
        if(!this.canParse(textualRepresentation)) {
            throw new ParsingException("Can't find a textual representation that can be parsed.");
        }
        Matcher matcher = this.getSearchPattern().matcher(textualRepresentation);
        objectHolder.setObject(this.getEnumerationDictionary().get(matcher.group(0)));
        return matcher.end(0);
    }

    /**
     * Returns a clone of the enumerationDictionary containing a set where the keys are the textual representation of the values.
     * @return a clone of the enumerationDictionary containing a set where the keys are the textual representation of the values.
     */
    public Map<String, T> getEnumerationDictionary() {
        return Collections.unmodifiableMap(enumerationDictionary);
    }

    /**
     * Returns a pattern that can search for a value of the specified enumeration.
     * @return a pattern that can search for a value of the specified enumeration.
     */
    public Pattern getSearchPattern() {
        return this.searchPattern;
    }

}
