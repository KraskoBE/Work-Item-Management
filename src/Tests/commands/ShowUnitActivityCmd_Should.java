package Tests.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowUnitActivityCmd;
import com.company.engine.commands.UnassignCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.unit.BoardImpl;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShowUnitActivityCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Team team;
    private Board board;
    private Member member;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        team = new TeamImpl("otbor");
        board = new BoardImpl("board");
        member = new MemberImpl("krasen");
        team.getBoards().put(board.getName(), board);
        team.getMembers().put(member.getName(), member);
        engine.getTeams().put(team.getName(), team);
        engine.getBoards().put(board.getName(), board);
        engine.getMembers().put(member.getName(), member);
    }

    @Test
    public void showMemberActivityTest() {
        parameters = Collections.singletonList(member.getName());
        member.addActivity("new activity");

        String result = ShowUnitActivityCmd.execute(engine, parameters, "person");
        String expected = String.format("[%s's activity]", member.getName())
                + "\nPerson created"
                + "\nnew activity";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void showBoardActivityTest() {
        parameters = Collections.singletonList(board.getName());
        board.addActivity("new activity");

        String result = ShowUnitActivityCmd.execute(engine, parameters, "board");
        String expected = String.format("[%s's activity]", board.getName())
                + "\nBoard created"
                + "\nnew activity";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorInvalidUnitTest() {
        parameters = Collections.singletonList("nemaBoard");
        String result = ShowUnitActivityCmd.execute(engine, parameters, "board");
        String expected = String.format(EngineConstants.BoardDoesNotExistErrorMessage, "nemaBoard");
        Assert.assertEquals(expected, result);

        parameters = Collections.singletonList("nemaPerson");
        result = ShowUnitActivityCmd.execute(engine, parameters, "person");
        expected = String.format(EngineConstants.PersonDoesNotExistErrorMessage, "nemaPerson");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorInvalidParameters() {
        String result = ShowUnitActivityCmd.execute(engine, parameters, "unit");
        String expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("a", "b");
        result = ShowUnitActivityCmd.execute(engine, parameters, "unit");
        expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);
    }
}
