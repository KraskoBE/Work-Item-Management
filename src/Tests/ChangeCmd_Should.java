package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ChangeCmd;
import com.company.models.TeamImpl;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Size;
import com.company.models.common.Status;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;
import com.company.models.unit.BoardImpl;
import com.company.models.unit.MemberImpl;
import com.company.models.workItem.BugImpl;
import com.company.models.workItem.StoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Member member;
    private Team team;
    private Board board;
    private Bug bug;
    private Story story;
    private Feedback feedback;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        member = new MemberImpl("krasen");
        team = new TeamImpl("otbor");
        board = new BoardImpl("board");
        bug = new BugImpl(0, "imeNaBugaa", "description na bug", Priority.High, Severity.Critical, Status.Active);
        story = new StoryImpl(1, "imeNaStory", "description na story", Priority.High, Size.Large);

        board.addWorkItem(bug);
        board.addWorkItem(story);
        team.getBoards().put(board.getName(), board);
        team.getMembers().put(member.getName(), member);

        engine.getTeams().put(team.getName(), team);
        engine.getBoards().put(board.getName(), board);
        engine.getMembers().put(member.getName(), member);
        engine.getWorkItems().put(bug.getId(), bug);
        engine.getWorkItems().put(story.getId(), story);
    }

    @Test
    public void changeBugTests() {
        parameters = Arrays.asList(String.valueOf(bug.getId()), "status", "fixed");
        String result = ChangeCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, bug.getTitle(), "status", "fixed");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(bug.getId()), "priority", "medium");
        result = ChangeCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, bug.getTitle(), "priority", "medium");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(bug.getId()), "severity", "major");
        result = ChangeCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, bug.getTitle(), "severity", "major");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(bug.getId()), "invalidType", "invalid");
        result = ChangeCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.InvalidObjectTypeErrorMessage, "invalidType");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(bug.getId()), "status", "new");
        result = ChangeCmd.execute(engine, parameters);
        expected = "Invalid status";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void changeStoryTests() {
        parameters = Arrays.asList(String.valueOf(story.getId()), "priority", "medium");
        String result = ChangeCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, story.getTitle(), "priority", "medium");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(story.getId()), "size", "small");
        result = ChangeCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, story.getTitle(), "size", "small");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(story.getId()), "status", "notdone");
        result = ChangeCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, story.getTitle(), "status", "notdone");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(story.getId()), "invalidType", "invalid");
        result = ChangeCmd.execute(engine, parameters);
        expected = String.format(EngineConstants.InvalidObjectTypeErrorMessage, "invalidType");
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList(String.valueOf(story.getId()), "status", "new");
        result = ChangeCmd.execute(engine, parameters);
        expected = "Invalid status";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void changeFeedbackTests()
    {

    }

}
