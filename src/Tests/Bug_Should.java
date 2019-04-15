package Tests;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.workItem.BugImpl;
import org.junit.Test;

public class Bug_Should {

    @Test
    public void createBugWhenValidParametersArePassed() {
        BugImpl bug = new BugImpl(0, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.Active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsSmallerThanValue() {
        BugImpl bug = new BugImpl(0, "title", "description of the bug", Priority.High, Severity.Critical, Status.Active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsLargerThanValue() {
        BugImpl bug = new BugImpl(0, "tooLooooooooooooooooooooooooooooooooooooooooooooong", "description of the bug", Priority.High, Severity.Critical, Status.Active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDescriptionIsSmallerThanValue() {
        BugImpl bug = new BugImpl(0, "titleOfBug", "descr", Priority.High, Severity.Critical, Status.Active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDescriptionIsLargerThanValue() {
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 501; i++)
            description.append("a");

        BugImpl bug = new BugImpl(0, "titleOfBug", description.toString(), Priority.High, Severity.Critical, Status.Active);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatus0IsInvalid() {
        BugImpl bug0 = new BugImpl(0, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.NotDone);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatus1IsInvalid() {
        BugImpl bug1 = new BugImpl(1, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.InProgress);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatus2IsInvalid() {
        BugImpl bug2 = new BugImpl(2, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.Done);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatus3IsInvalid() {
        BugImpl bug3 = new BugImpl(3, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.New);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatus4IsInvalid() {
        BugImpl bug4 = new BugImpl(4, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.Scheduled);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenStatus5IsInvalid() {
        BugImpl bug5 = new BugImpl(5, "titleOfBug", "description of the bug", Priority.High, Severity.Critical, Status.Unscheduled);
    }
}
