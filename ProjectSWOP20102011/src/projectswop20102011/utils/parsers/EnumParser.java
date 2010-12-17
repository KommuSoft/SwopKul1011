package projectswop20102011.utils.parsers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.Parser;

/**
 * A parser that can parse enumeration values with a given enumeration.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EnumParser<T extends Enum> extends Parser<T> {

    public EnumParser(Class<T> enumClass) {
        super(makeRegex(enumClass));
    }

    private static <T extends Enum> String makeRegex(Class<T> enumClass) {
        try {
            Method m = enumClass.getMethod("values", new Class[0]);
            T[] elements = (T[]) m.invoke(null, new Object[0]);
            if (elements.length > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(elements[0].name());
                for(int i = 1; i < elements.length; i++) {
                    sb.append(String.format("|%s",elements[i].name()));
                }
                //TODO onderstaande regel mag weg waarschijnlijk
				//System.out.println(sb.toString());
                return sb.toString();
            }
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
        return "";
    }

    @Override
    protected T parseFromMatcher(Matcher matcher) throws ParsingException {
        return null;
    }
}
