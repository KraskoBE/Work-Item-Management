package Tests;

import com.company.models.CommentImpl;
import com.company.models.unit.MemberImpl;
import org.junit.Test;

public class Comment_Should {

    @Test
    public void createCommentWhenValidParametersArePassed() {
        CommentImpl comment = new CommentImpl(new MemberImpl("newMember"), "message");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenMessageIsEmpty() {
        CommentImpl comment = new CommentImpl(new MemberImpl("newMember"), "");
    }
}
