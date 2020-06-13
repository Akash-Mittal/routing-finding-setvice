package com.am.bp.innovations.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 7045483476273857900L;
    // Status Codes and Messages
    // Needed For Business Decisions andJunits

    public BusinessException() {
        super();

    }

    public BusinessException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
            boolean paramBoolean2) {
        super(paramString, paramThrowable, paramBoolean1, paramBoolean2);

    }

    public BusinessException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);

    }

    public BusinessException(String paramString) {
        super(paramString);

    }

    public BusinessException(Throwable paramThrowable) {
        super(paramThrowable);

    }
}
