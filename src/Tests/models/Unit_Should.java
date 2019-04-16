package Tests.models;

import com.company.models.unit.BoardImpl;
import com.company.models.unit.MemberImpl;
import org.junit.Test;

public class Unit_Should {
    @Test
    public void createMemberWhenValidParametersArePassed() {
        MemberImpl member = new MemberImpl("Member");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenMemberNameIsSmallerThanValue() {
        MemberImpl member = new MemberImpl("1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenMemberNameIsLargerThanValue() {
        MemberImpl member = new MemberImpl("0123456789123456");
    }

    @Test
    public void createBoardWhenValidParametersArePassed() {
        BoardImpl board = new BoardImpl("BoardName");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenBoardNameIsSmallerThanValue() {
        BoardImpl board = new BoardImpl("1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenBoardNameIsLargerThanValue() {
        BoardImpl board = new BoardImpl("0123456789123456");
    }
}
