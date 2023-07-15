import java.util.*;

public class LibraryController extends Controller<Library> {

    private Library library = this.getObjInstance();

    public LibraryController(Library library) {
        super(library);
    }

    @ActionRouter(actionName = "addBook")
    public String addBook(String type){
        try {
            Book.BookType bookType = Book.bookMap.get(type);
            Book book = (Book) bookType.getCls().newInstance();
            library.addBook(book);
            String response = String.format("Created new book: %s [id: %d]",book.getClass().getName(), book.getId());
            return response;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new IllegalArgumentException("Book type does not exist!!!");
        }
    }

    @ActionRouter(actionName = "addMember")
    public String  addMember(String type) throws ControllerExceptions {
        try {

            Class<?> memberCls = Member.memberRouter.get(type);
            Member member = (Member) memberCls.newInstance();
            library.addMember(member);
            String response = String.format("Created new member: %s [id: %d]",member.getName(), member.getId());
            return response;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new IllegalArgumentException("Member type does not exist!!!");
        }
    }
    @ActionRouter(actionName = "borrowBook")
    public String borrowBook(int bookId, int memberId, Date date) throws LibraryExceptions{
        Book book = library.getBookById(bookId);
        Member member = library.getMemberById(memberId);
        Borrow borrow  = library.borrowBook(book,member,date);
        return borrow.toString();
    }
    @ActionRouter(actionName = "returnBook")
    public String returnBook(int bookId, int memberId,Date date) throws LibraryExceptions{
        Book book = library.getBookById(bookId);
        Member member = library.getMemberById(memberId);

        if(!member.isContainsBook(book)){
            throw new LibraryExceptions.BookTransactionException("Member not borrow that book!");
        }
        Transaction transaction = library.returnBook(book,member,date);

        return String.format(
                "The book [%d] was returned by member [%d] at %s Fee: %d",
                book.getId(),
                member.getId(),
                Utilities.convertDateToString(date),
                transaction.getFee(date)
        );
    }

    @ActionRouter(actionName = "extendBook")
    public String extendBook(int bookId, int memberId,Date date) throws LibraryExceptions{
        Book book = library.getBookById(bookId);
        Member member = library.getMemberById(memberId);
        if(!book.getTransaction().getClass().equals(Borrow.class)){
            throw new LibraryExceptions.BookTransactionException("You cannot extend the deadline!");
        }
        Transaction transaction = library.extendBook(book,member,date);
        String response = String.format(
                "The deadline of book [%d] was extended by member [%d] at %s\n",
                book.getId(),
                member.getId(),
                Utilities.convertDateToString(date)
        );
        response += String.format(
                "New deadline of book [%d] is %s",
                book.getId(),
                Utilities.convertDateToString(transaction.getDueDate().get())
        );
        return  response;
    }

    @ActionRouter(actionName = "readInLibrary")
    public String readInLibrary(int bookId, int memberId,Date date) throws LibraryExceptions{
        Book book = library.getBookById(bookId);
        Member member = library.getMemberById(memberId);

        if (!book.isFree()){
            throw new LibraryExceptions("You can not read this book!");
        }
        Read read = library.readBook(book,member,date);
        return read.toString();
    }
    @ActionRouter(actionName = "getTheHistory")
    public String getTheHistory(){
        return library.toString();
    }
}
