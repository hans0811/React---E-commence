package com.ecommerce.hans.exception;

public class RuntimeServiceException extends RuntimeException{

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    public RuntimeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeServiceException(String message) {
        super(message);
    }

    public RuntimeServiceException(Throwable cause) {
        super(cause);
    }
}
