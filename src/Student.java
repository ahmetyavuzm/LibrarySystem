@MemberInfo(borrowLimit = 2, loanDay = 7, accessableBooks = {Printed.class})
public class Student extends Member {
    public Student() {
        super();
    }

    @Override
    public String toString() {
        return String.format("Student [id: %d]", this.getId());
    }

    @Override
    public String getName() {
        return "Student";
    }
}
