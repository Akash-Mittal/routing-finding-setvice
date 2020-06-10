package com.am.bp.alf.innovations.service.domain.exception;

public class ServiceException extends BusinessException {

    private static final long serialVersionUID = -2292123407413311538L;

    public ServiceException() {
        super();

    }

    public ServiceException(String paramString, Throwable paramThrowable, boolean paramBoolean1,
            boolean paramBoolean2) {
        super(paramString, paramThrowable, paramBoolean1, paramBoolean2);

    }

    public ServiceException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);

    }

    public ServiceException(String paramString) {
        super(paramString);

    }

    public ServiceException(Throwable paramThrowable) {
        super(paramThrowable);

    }

}
