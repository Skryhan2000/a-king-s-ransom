package com.loneliness.client.service;

public class ServiceException extends Throwable {
    private StringBuilder exceptionMessage=new StringBuilder();
    private StringBuilder exception=new StringBuilder();

    public ServiceException(String exceptionMessage,String exception){
        this.exceptionMessage.append(exceptionMessage);
        this.exception.append(exception);
    }

    public ServiceException(StringBuilder exception) {
        this.exception = exception;
    }

    public StringBuilder getExceptionMessage() {
        return exceptionMessage;
    }

    public StringBuilder getException() {
        return exception;
    }
}
