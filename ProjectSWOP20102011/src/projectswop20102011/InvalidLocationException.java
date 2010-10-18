package projectswop20102011;

public class InvalidLocationException extends Exception{
	/**
     * Variable registering the serivalVersionUID of this InvalidLocationException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an InvalidLocationException with no detail message.
     */
    public InvalidLocationException(){
        super();
    }

    /**
     * Constructs an InvalidLocationException with the specified detail message.
     * @param message
     *      The detail message.
     */
    public InvalidLocationException(String message){
        super(message);
    }
}