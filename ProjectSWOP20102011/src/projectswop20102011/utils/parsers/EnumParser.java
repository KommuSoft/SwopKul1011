package projectswop20102011.utils.parsers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public EnumParser(Class<T> enumClass) {
        this.enumerationDictionary = buildEnumerationDictionary(enumClass);
        this.searchPattern = generatePattern();
    }

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
    private Pattern generatePattern () {
        StringBuilder sbRegex = new StringBuilder();
        Enumeration<String> enumeration = this.enumerationDictionary.keys();
        if(enumeration.hasMoreElements()) {
            sbRegex.append(enumeration.nextElement());
            while(enumeration.hasMoreElements()) {
                sbRegex.append(String.format("|%s",enumeration.nextElement()));
            }
        }
        return Pattern.compile(sbRegex.toString());
    }

    @Override
    public boolean canParse(String textualRepresentation) {
        
    }

    @Override
    public int parse(String textualRepresentation, ObjectHolder<? super T> objectHolder) throws ParsingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
