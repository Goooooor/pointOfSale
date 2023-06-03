package se.kth.iv1350.pos.integration;

/**
 * If an operation is failed this exception is thrown.
 */
public class OperationFailedException extends Exception {

    /**
     * Creates an instance of <code>OperationFailedException</code>.
     * 
     * @param msg   The message of the exception.
     * @param cause The cause of the exception.
     */
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}
