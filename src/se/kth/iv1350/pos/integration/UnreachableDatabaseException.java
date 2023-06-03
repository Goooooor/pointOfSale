package se.kth.iv1350.pos.integration;

/**
 * Thrown when the database is not reachable.
 */
public class UnreachableDatabaseException extends Exception {

    /**
     * Creates a new instance of <code>UnreachableDatabaseException</code>
     * exception.
     * 
     * @param msg The message followed by the exception.
     */
    public UnreachableDatabaseException(String message) {
        super(message);
    }
}
