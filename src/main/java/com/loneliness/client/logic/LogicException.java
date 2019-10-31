package com.loneliness.client.logic;

public class LogicException {
    private StringBuilder exceptionMessage=new StringBuilder();
    private StringBuilder exception=new StringBuilder();

    public LogicException(String exceptionMessage,String exception){
        this.exceptionMessage.append(exceptionMessage);
        this.exception.append(exception);
    }

    public LogicException(StringBuilder exception) {
        this.exception = exception;
    }

    public StringBuilder getExceptionMessage() {
        return exceptionMessage;
    }

    public StringBuilder getException() {
        return exception;
    }
}
