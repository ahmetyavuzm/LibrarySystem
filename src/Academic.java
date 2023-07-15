import java.util.ArrayList;
import java.util.Arrays;

@MemberInfo(borrowLimit = 4, loanDay = 14, accessableBooks = {Printed.class,Handwritten.class})
public class Academic extends Member{
    public Academic() {
        super();
    }

    @Override
    public String toString() {
        return String.format("Academic [id: %d]", this.getId());
    }

    @Override
    public String getName() {
        return "Academic";
    }
}
