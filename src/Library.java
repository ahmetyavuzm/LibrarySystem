import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }
    public void addMember(Member member){
        members.add(member);
    }
    public void removeBook(Book book){
        books.remove(book);
    }
    public void removeMember(Member member){
        members.remove(member);
    }

    public Book getBookById(int id) throws LibraryExceptions.IllegalArgumentException {
        Optional<Book> opBook = Optional.ofNullable(books.stream().filter(b->b.getId() == id).collect(Collectors.toList()).get(0));
        Book book = opBook.orElseThrow(()-> new LibraryExceptions.IllegalArgumentException("There is no such a book!"));
        return book;
    }

    public Member getMemberById(int id) throws LibraryExceptions.IllegalArgumentException {
        Optional<Member> opMember = Optional.ofNullable(members.stream().filter(m->m.getId() == id).collect(Collectors.toList()).get(0));
        Member member = opMember.orElseThrow(()-> new LibraryExceptions.IllegalArgumentException("There is no such a member!"));
        return member;
    }

    public Borrow borrowBook(Book book, Member member, Date date) throws LibraryExceptions {
        Borrow borrow = new Borrow(book,member,date);
        transactions.add(borrow);
        return borrow;
    }

    public Read readBook(Book book, Member member, Date date) throws LibraryExceptions{
        Read read =  new Read(book,member,date);
        transactions.add(read);
        return read;
    }

    public Transaction returnBook(Book book, Member member, Date date){
        Transaction transaction =  book.getTransaction();
        book.setTransaction(null);
        transaction.getMember().deleteBookTransaction(transaction);
        transactions.remove(transaction);
        return transaction;
    }

    public Transaction extendBook(Book book, Member member, Date date) throws LibraryExceptions.BookTransactionException {
        Borrow borrow = (Borrow) book.getTransaction();
        if(borrow.isExtend()){
           throw new  LibraryExceptions.BookTransactionException("You cannot extend the deadline!");
        }
        borrow.setExtend(true);
        return borrow;
    }

    @Override
    public String toString() {
        List<Student> students = getSubList(members,Student.class);
        List<Academic> academics =  getSubList(members,Academic.class);
        List<Handwritten> handwrittens =getSubList(books,Handwritten.class);
        List<Printed> printeds =  getSubList(books,Printed.class);
        List<Borrow> borrows = getSubList(transactions,Borrow.class);
        List<Read> reads = getSubList(transactions,Read.class);

        String stringForm = "";
        stringForm = "History of library:\n";

        stringForm += String.format("\nNumber of students: %d\n",students.size());
        stringForm += joinList(students,"\n");

        stringForm += String.format("\n\nNumber of academics: %d\n", academics.size());
        stringForm += joinList(academics,"\n");

        stringForm += String.format("\n\nNumber of printed books: %d\n",printeds.size());
        stringForm += joinList(printeds,"\n");

        stringForm += String.format("\n\nNumber of handwritten books: %d\n",handwrittens.size());
        stringForm += joinList(handwrittens,"\n");

        stringForm += String.format("\n\nNumber of borrowed books: %d\n",borrows.size());
        stringForm += joinList(borrows,"\n");

        stringForm += String.format("\n\nNumber of books read in library: %d\n",reads.size());
        stringForm += joinList(reads,"\n");

        return stringForm;
    }

    private <T,U extends T> List<U> getSubList(List<T> list, Class<U> subCls){
        return (List<U>) list.stream().filter(m -> m.getClass().equals(subCls)).collect(Collectors.toList());
    }
    
    private String joinList(List<?> list, String delimiter){
        return list.stream().flatMap(obj->Stream.of(obj.toString())).collect(Collectors.joining(delimiter));
    }


}
