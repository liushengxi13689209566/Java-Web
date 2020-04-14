package com.huarun.exception;

/**
 * 业务层异常的父类
 */
public class ServiceException extends Exception {
    private String exceptionDesc;

    public ServiceException(Exception e) {
        super(e);
    }

    ServiceException(Exception e, String exceptionDesc) {
        super(e);
        this.exceptionDesc = exceptionDesc;
    }

    ServiceException(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    ServiceException() {
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }
}
