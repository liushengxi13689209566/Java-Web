package com.huarun.exception;

public class SignCaseServiceException extends ServiceException {

    public SignCaseServiceException() {
        super();
    }

    public SignCaseServiceException(Exception e, String exception) {
        super(e, exception);
    }

    public SignCaseServiceException(Exception e) {
        super(e);
    }
}