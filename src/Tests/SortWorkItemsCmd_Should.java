package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.SortWorkItemsCmd;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Size;
import com.company.models.common.Status;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;
import com.company.models.workItem.BugImpl;
import com.company.models.workItem.FeedbackImpl;
import com.company.models.workItem.StoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortWorkItemsCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Bug bug1;
    private Bug bug2;
    private Story story1;
    private Story story2;
    private Feedback feedback1;
    private Feedback feedback2;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        bug1 = new BugImpl(0, "ffffffffff", "description of bug1", Priority.Medium, Severity.Major, Status.Fixed);
        bug2 = new BugImpl(1, "eeeeeeeeee", "description of bug2", Priority.High, Severity.Critical, Status.Active);
        story1 = new StoryImpl(2, "dddddddddd", "description of story1", Priority.Low, Size.Small);
        story1.setStatus(Status.Done);
        story2 = new StoryImpl(3, "cccccccccc", "description of story2", Priority.Medium, Size.Medium);
        story2.setStatus(Status.InProgress);
        feedback1 = new FeedbackImpl(4, "bbbbbbbbbb", "description of feedback1", Status.Done, 150);
        feedback2 = new FeedbackImpl(5, "aaaaaaaaaa", "description of feedback2", Status.New, 100);

        engine.getWorkItems().put(bug1.getId(), bug1);
        engine.getWorkItems().put(bug2.getId(), bug2);
        engine.getWorkItems().put(story1.getId(), story1);
        engine.getWorkItems().put(story2.getId(), story2);
        engine.getWorkItems().put(feedback1.getId(), feedback1);
        engine.getWorkItems().put(feedback2.getId(), feedback2);
    }

    @Test
    public void sortByTitleTest() {
        parameters = Collections.singletonList("title");

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n",
                feedback2.toString(), feedback1.toString(),
                story2.toString(), story1.toString(),
                bug2.toString(), bug1.toString()
        );

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByPriorityTest() {
        parameters = Collections.singletonList("priority");

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n",
                bug2.toString(), bug1.toString(),
                story2.toString(), story1.toString()
        );

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortBySeverityTest() {
        parameters = Collections.singletonList("severity");

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n",
                bug2.toString(), bug1.toString()
        );

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortBySizeTest() {
        parameters = Collections.singletonList("size");

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n",
                story2.toString(), story1.toString()
        );

        Assert.assertEquals(expected, result);
    }

    @Test
    public void sortByRatingTest() {
        parameters = Collections.singletonList("rating");

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n",
                feedback2.toString(), feedback1.toString()
        );

        Assert.assertEquals(expected, result);
    }

    @Test
    public void invalidSortTypeTest() {
        parameters = Collections.singletonList("invalidSortType");

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.InvalidSortTypeErrorMessage, "invalidSortType");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void invalidParameterCountTest() {

        String result = SortWorkItemsCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("a", "b");
        result = SortWorkItemsCmd.execute(engine, parameters);
        expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);
    }
}
