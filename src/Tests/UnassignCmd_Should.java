package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.UnassignCmd;
import com.company.models.TeamImpl;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.unit.BoardImpl;
import com.company.models.unit.MemberImpl;
import com.company.models.workItem.BugImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnassignCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Member member;
    private Team team;
    private Board board;
    private Bug bug;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        member = new MemberImpl("krasen");
        team = new TeamImpl("otbor");
        board = new BoardImpl("board");
        bug = new BugImpl(0, "imeNaBugaa", "description na bug", Priority.High, Severity.Critical, Status.Active);
        bug.setAssignee(member);

        board.addWorkItem(bug);
        team.getBoards().put(board.getName(), board);
        team.getMembers().put(member.getName(), member);
        member.addWorkItem(bug);

        engine.getTeams().put(team.getName(), team);
        engine.getBoards().put(board.getName(), board);
        engine.getMembers().put(member.getName(), member);
        engine.getWorkItems().put(bug.getId(), bug);
    }

    @Test
    public void UnassignWithValidParameters() {
        parameters = Arrays.asList(String.valueOf(bug.getId()), member.getName());

        String result = UnassignCmd.execute(engine, parameters);
        String expected = EngineConstants.WorkItemUnassignedSuccessMessage;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenWorkItemDoesNotExist() {
        parameters = Arrays.asList(String.valueOf(1), member.getName());

        String result = UnassignCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, 1);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMemberDoesNotExist() {
        parameters = Arrays.asList(String.valueOf(bug.getId()), "stoyan");

        String result = UnassignCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.MemberDoesNotExistErrorMessage, "stoyan");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMemberDoesNotHaveWorkItem() {
        member.removeWorkItem(0);
        parameters = Arrays.asList(String.valueOf(bug.getId()), member.getName());

        String result = UnassignCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.MemberDoesNotHaveWorkItemErrorMessage, member.getName(), bug.getId());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c");

        String result = UnassignCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {
        parameters = Collections.singletonList("a");

        String result = UnassignCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }
}
