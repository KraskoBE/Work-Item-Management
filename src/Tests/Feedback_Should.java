package Tests;

import com.company.models.common.Status;
import com.company.models.workItem.FeedbackImpl;
import org.junit.Test;

public class Feedback_Should {

    @Test
    public void createFeedbackWhenValidParametersArePassed() {
        FeedbackImpl feedback = new FeedbackImpl(0,"titleOfFeed","description", Status.New,100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsSmallerThanValue() {
        FeedbackImpl feedback = new FeedbackImpl(0,"title","description", Status.New,100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsLargerThanValue() {
        FeedbackImpl feedback = new FeedbackImpl(0,"tooLooooooooooooooooooooooooooooooooooooooooooooong","description", Status.New,100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatusIsInvalid() {

        FeedbackImpl feedback = new FeedbackImpl(0,"titleOfFeed","description", Status.Active,100);
    }
}
