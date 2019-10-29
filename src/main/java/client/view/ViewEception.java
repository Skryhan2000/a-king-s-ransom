package client.view;

public class ViewEception {
    private StringBuilder exceptionMessage=new StringBuilder();
    private StringBuilder exception=new StringBuilder();

    public ViewEception(String exceptionMessage,String exception){
        this.exceptionMessage.append(exceptionMessage);
        this.exception.append(exception);
    }

    public ViewEception(StringBuilder exception) {
        this.exception = exception;
    }

    public StringBuilder getExceptionMessage() {
        return exceptionMessage;
    }

    public StringBuilder getException() {
        return exception;
    }
}
