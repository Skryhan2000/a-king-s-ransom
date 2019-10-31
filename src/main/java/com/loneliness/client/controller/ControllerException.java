package com.loneliness.client.controller;

public class ControllerException {

        private StringBuilder exceptionMessage=new StringBuilder();
        private StringBuilder exception=new StringBuilder();

        public ControllerException(String exceptionMessage,String exception){
            this.exceptionMessage.append(exceptionMessage);
            this.exception.append(exception);
        }

        public ControllerException(StringBuilder exception) {
            this.exception = exception;
        }

        public StringBuilder getExceptionMessage() {
            return exceptionMessage;
        }

        public StringBuilder getException() {
            return exception;
        }


}
