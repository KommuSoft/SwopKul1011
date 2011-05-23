package projectswop20102011.exceptions;


public class InvalidAddedDisasterException extends Exception{
    /**
     * Creates a new instance of <code>InvalidAddedDisasterException</code> without detail message.
     */
    public InvalidAddedDisasterException() {
    }


    /**
     * Constructs an instance of <code>InvalidAddedDisasterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidAddedDisasterException(String msg) {
        super(msg);
    }
}
