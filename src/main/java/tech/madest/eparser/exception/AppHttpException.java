package tech.madest.eparser.exception;

public class AppHttpException extends Exception{

    public AppHttpException() {
        super();
    }

    public AppHttpException(String message) {
        super(message);
    }

    public AppHttpException(String p_arg0, Throwable p_arg1) {
        super(p_arg0, p_arg1);
    }

    public AppHttpException(Throwable p_arg0) {
        super(p_arg0);
    }

}