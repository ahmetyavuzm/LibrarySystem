import java.util.Date;

public class Borrow extends Transaction {
    private boolean isExtend = false;

    public Borrow(Book book, Member member, Date barrowDate) throws LibraryExceptions {
        super(book, member, barrowDate,
                new Date(
                        barrowDate.getTime() +
                        Utilities.convertDateToMilliSecond(member.getMemberInfo().loanDay()
                )));
        checkBorrow();
        book.setTransaction(this);
        member.addBookTransaction(this);
        member.setBorrowNum(member.getBorrowNum()+1);
    }

    public boolean isExtend() {
        return isExtend;
    }

    public void setExtend(boolean extend) {
        isExtend = extend;
        setDueDate(new Date(
                this.getDueDate().get().getTime() +
                Utilities.convertDateToMilliSecond(this.getMember().getMemberInfo().loanDay())
        ));
    }
    private void checkBorrowNum() throws LibraryExceptions.BookTransactionException {
        MemberInfo info = this.getMember().getMemberInfo();
        if (info.borrowLimit() == this.getMember().getBorrowNum()){
            throw new LibraryExceptions.BookTransactionException("You have exceeded the borrowing limit!");
        }
    }
    private void checkBorrow() throws LibraryExceptions {
        checkIsFree();
        checkBorrowNum();
        checkAccess();
    }

    @Override
    public String toString() {
        return String.format(
                "The book [%d] was borrowed by member [%d] at %s",
                this.getBook().getId(),
                this.getMember().getId(),
                Utilities.convertDateToString(this.getBarrowDate())
        );
    }
}
