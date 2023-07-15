import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberInfo {
    int borrowLimit();
    int loanDay();
    Class<?>[] accessableBooks();

}
