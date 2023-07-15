import java.util.Date;

public class Read extends Transaction {
    protected Read(Book book, Member member, Date barrowDate) throws LibraryExceptions{
        super(book, member, barrowDate, null);
        checkRead();
        book.setTransaction(this);
        member.addBookTransaction(this);
    }
    public void checkRead() throws LibraryExceptions {
        checkIsFree();
        checkAccess();
    }

    @Override
    public String toString() {
        return String.format("The book [%d] was read in library by member [%d] at %s",
                this.getBook().getId(),
                this.getMember().getId(),
                Utilities.convertDateToString(this.getBarrowDate())
        );
    }
}
