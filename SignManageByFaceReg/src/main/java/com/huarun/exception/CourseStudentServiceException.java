package com.huarun.exception;

public class CourseStudentServiceException extends ServiceException {

    public CourseStudentServiceException() {
        super();
    }

    public CourseStudentServiceException(Exception e, String exception) {
        super(e, exception);
    }

    public CourseStudentServiceException(Exception e) {
        super(e);
    }
}
