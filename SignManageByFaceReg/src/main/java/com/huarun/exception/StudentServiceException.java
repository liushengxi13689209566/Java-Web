package com.huarun.exception;

public class StudentServiceException extends ServiceException {
    public StudentServiceException() {
        super();
    }

    public StudentServiceException(Exception e, String exception) {
        super(e, exception);
    }

    public StudentServiceException(Exception e) {
        super(e);
    }
}
