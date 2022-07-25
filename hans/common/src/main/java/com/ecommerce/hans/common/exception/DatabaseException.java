package com.ecommerce.hans.common.exception;

public class DatabaseException extends Exception{
    private static final long serialVersionUID = -3671274381181538749L;

    public DatabaseException() {
        super();
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param sMsg
     * @param cause
     */
    public DatabaseException(String sMsg, Throwable cause) {
        super(sMsg, cause);
    }

    /**
     * Constructor
     *
     * @param sMsg
     */
    public DatabaseException(String sMsg) {
        super(sMsg);
    }

    @Override
    public String toString() {

        Throwable th = getCause();
        StringBuilder sb = new StringBuilder(super.toString());
        if (th != null) {
            sb.append(", caused = " + th);
        }

        return sb.toString();
    }
}
