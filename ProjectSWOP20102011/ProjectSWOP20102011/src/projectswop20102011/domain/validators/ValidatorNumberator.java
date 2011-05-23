package projectswop20102011.domain.validators;

/**
 * An interface providing that implements a validator and numberator
 * @param <T> The type of objects that will be validated and numberated
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface ValidatorNumberator<T> extends Numberator<T>, Validator<T> {
}
