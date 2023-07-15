import java.util.ArrayList;
import java.util.HashMap;

public abstract class Member {
    private static int idCounter = 1;
    private int id;
    private int borrowNum = 0;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public static HashMap<String,Class<?>> memberRouter = new HashMap<>();
    static {
       memberRouter.put("S",Student.class);
       memberRouter.put("A",Academic.class);
    }

    protected Member() {
        setId();
    }


    public int getId() {
        return id;
    }

    public ArrayList<Transaction> getBooks() {
        return transactions;
    }

    public int getBorrowNum() {
        return borrowNum;
    }


    private void setId() {
        this.id = idCounter;
        idCounter++;
    }

    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }

    public void addBookTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void deleteBookTransaction(Transaction transaction){
        this.transactions.remove(transaction);
    }

    public boolean isContainsBook(Book book){
        return this.transactions.stream().anyMatch(bt -> bt.getBook() == book);
    }

    public abstract String getName();

    public MemberInfo getMemberInfo(){
        return this.getClass().getDeclaredAnnotation(MemberInfo.class);
    }

}
