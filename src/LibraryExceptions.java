public class LibraryExceptions extends Exception{
    public LibraryExceptions(String message) {
        super(message);
    }

    public static class IllegalArgumentException extends LibraryExceptions{
        public IllegalArgumentException(String message) {
            super(message);
        }
    }

    public static class BookTransactionException extends LibraryExceptions{
        public BookTransactionException(String message) {
            super(message);
        }
    }

    public static class AccessException extends LibraryExceptions{
        public AccessException(String message) {
            super(message);
        }
    }

}
