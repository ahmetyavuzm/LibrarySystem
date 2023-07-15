import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public abstract class Transaction {
    private Book book;
    private Member member;
    private Date barrowDate;
    private Date dueDate;

    protected Transaction(Book book, Member member, Date barrowDate, Date dueDate) {
        this.book = book;
        this.member = member;
        this.barrowDate = barrowDate;
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public Date getBarrowDate() {
        return barrowDate;
    }

    public Optional<Date> getDueDate() {
        return Optional.ofNullable(dueDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    protected void checkIsFree() throws LibraryExceptions.BookTransactionException {
        if (!this.book.isFree()){
            throw new LibraryExceptions.BookTransactionException("You cannot borrow this book because of " + book.getTransaction().toString());
        }
    }

    protected void checkAccess() throws LibraryExceptions.AccessException {
        MemberInfo info = this.member.getMemberInfo();
        if (!Arrays.stream(info.accessableBooks()).anyMatch(i->i.equals(this.book.getClass()))){
            throw new LibraryExceptions.AccessException(
                    String.format(
                            "%ss can not read %s books!",
                            this.member.getName(),
                            this.book.getClass().getName().toLowerCase()
                    )
            );
        }
    }

    public int getFee(Date returnDate){
        double fee = returnDate.compareTo(this.getDueDate().orElse(returnDate));
        return fee < 0 ? 0: (int) fee;
    }

}
