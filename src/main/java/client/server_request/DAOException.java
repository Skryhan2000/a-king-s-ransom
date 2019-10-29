package client.server_request;

public class DAOException {

    private StringBuilder exceptionMessage=new StringBuilder();
    private StringBuilder exception=new StringBuilder();

    public DAOException(String exceptionMessage,String exception){
        this.exceptionMessage.append(exceptionMessage);
        this.exception.append(exception);
    }

    public DAOException(StringBuilder exception) {
        this.exception = exception;
    }

    public StringBuilder getExceptionMessage() {
        return exceptionMessage;
    }

    public StringBuilder getException() {
        return exception;
    }
}
