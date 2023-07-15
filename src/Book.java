import java.util.HashMap;

public abstract class Book {
    enum BookType{
        HANDWRITTEN(Handwritten.class),
        PRINTED(Printed.class);

        private final Class<?> cls;

        BookType(Class<?> cls) {
            this.cls = cls;
        }

        public Class<?> getCls() {
            return cls;
        }
    }

    public static HashMap<String,BookType> bookMap = new HashMap<>();

    static {
        bookMap.put("P",BookType.PRINTED);
        bookMap.put("H",BookType.HANDWRITTEN);
    }

    private static int idCounter = 1;
    private int id;
    private Transaction transaction = null;

    protected Book() {
        setId();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public int getId() {
        return id;

    }

    private void setId(){
        this.id = idCounter;
        idCounter++;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean isFree(){
        return this.transaction == null;
    }


}
