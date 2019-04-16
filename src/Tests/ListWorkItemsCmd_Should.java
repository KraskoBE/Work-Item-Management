package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ListWorkItemsCmd;
import com.company.models.CommentImpl;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Size;
import com.company.models.common.Status;
import com.company.models.contracts.Comment;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;
import com.company.models.unit.MemberImpl;
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

public class ListWorkItemsCmd_Should {

    private EngineImpl engine;
    private List<String> parameters;
    private Bug bug1;
    private Bug bug2;
    private Story story1;
    private Story story2;
    private Feedback feedback1;
    private Feedback feedback2;
    private Member member;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        member = new MemberImpl("krasen");
        engine.getMembers().put(member.getName(), member);
        bug1 = new BugImpl(0, "ffffffffff", "description of bug1", Priority.Medium, Severity.Major, Status.Fixed);
        bug1.setAssignee(member);
        bug2 = new BugImpl(1, "eeeeeeeeee", "description of bug2", Priority.High, Severity.Critical, Status.Active);
        story1 = new StoryImpl(2, "dddddddddd", "description of story1", Priority.Low, Size.Small);
        story1.setStatus(Status.Done);
        story2 = new StoryImpl(3, "cccccccccc", "description of story2", Priority.Medium, Size.Medium);
        story2.setStatus(Status.InProgress);
        story2.setAssignee(member);
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
    public void listByCategoryTest() {
        parameters = Collections.singletonList("bug");
        String result = ListWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n", bug1.toString(), bug2.toString());
        Assert.assertEquals(expected, result);

        parameters = Collections.singletonList("story");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.join("\n", story1.toString(), story2.toString());
        Assert.assertEquals(expected, result);

        parameters = Collections.singletonList("feedback");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.join("\n", feedback1.toString(), feedback2.toString());
        Assert.assertEquals(expected, result);

        parameters = Collections.singletonList("all");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.join("\n", bug1.toString(), bug2.toString(), story1.toString(), story2.toString(), feedback1.toString(), feedback2.toString());
        Assert.assertEquals(expected, result);

        parameters = Collections.singletonList("invalidCategory");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.InvalidCategoryErrorMessage, "invalidCategory");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void listByStatusTest() {
        parameters = Arrays.asList("status", "active");
        String result = ListWorkItemsCmd.execute(engine, parameters);
        String expected = bug2.toString();
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("status", "done");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.join("\n", story1.toString(), feedback1.toString());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void listByAssigneeTest() {
        parameters = Arrays.asList("assignee", member.getName());
        String result = ListWorkItemsCmd.execute(engine, parameters);
        String expected = String.join("\n", bug1.toString(), story2.toString());
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("assignee", "nemaMember");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.MemberDoesNotExistErrorMessage, "nemaMember");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void listByStatusAndAssigneeTest() {
        parameters = Arrays.asList("statusassignee", "fixed", member.getName());
        String result = ListWorkItemsCmd.execute(engine, parameters);
        String expected = bug1.toString();
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("statusassignee", "fixed", "nemaMember");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.MemberDoesNotExistErrorMessage, "nemaMember");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void invalidParameterstest() {
        String result = ListWorkItemsCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("a", "b", "c", "d");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("nemaParameter", "fixed", "abcd");
        result = ListWorkItemsCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.InvalidCommandErrorMessage, "nemaParameter");
        Assert.assertEquals(expected, result);
    }
}
