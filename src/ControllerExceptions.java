public class ControllerExceptions extends Exception{
    public ControllerExceptions(String message) {
        super(message);
    }

    public ControllerExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerExceptions(Throwable cause) {
        super(cause);
    }

    public static class ActionRouteException extends ControllerExceptions{
        public ActionRouteException(String message) {
            super(message);
        }

        public ActionRouteException(String message, Throwable cause) {
            super(message, cause);
        }

        public ActionRouteException(Throwable cause) {
            super(cause);
        }
    }

    public static class ActionInvokeException extends ControllerExceptions{
        public ActionInvokeException(String message) {
            super(message);
        }

        public ActionInvokeException(String message, Throwable cause) {
            super(message, cause);
        }

        public ActionInvokeException(Throwable cause) {
            super(cause);
        }
    }
    public static class ParseException extends ControllerExceptions{
        public ParseException(String message) {
            super(message);
        }

        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }

        public ParseException(Throwable cause) {
            super(cause);
        }
    }
}
