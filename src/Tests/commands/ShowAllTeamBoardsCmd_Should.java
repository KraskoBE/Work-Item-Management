package Tests.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowAllTeamBoardsCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.unit.BoardImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShowAllTeamBoardsCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Team team;
    private Board board1;
    private Board board2;


    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
    }

    @Test
    public void showAllBoardTest() {
        team = new TeamImpl("otbor");
        board1 = new BoardImpl("board1");
        board2 = new BoardImpl("board2");
        team.getBoards().put(board1.getName(), board1);
        team.getBoards().put(board2.getName(), board2);
        engine.getTeams().put(team.getName(), team);
        engine.getBoards().put(board1.getName(), board1);
        engine.getBoards().put(board2.getName(), board2);

        parameters = Collections.singletonList(team.getName());
        String result = ShowAllTeamBoardsCmd.execute(engine, parameters);
        String expected = String.format("[BOARDS of %s]\n", team.getName()) + String.join("\n", board1.getName(), board2.getName());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void invalidNumberOfParametersTest() {
        String result = ShowAllTeamBoardsCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void invalidTeamTest() {
        parameters = Collections.singletonList("nemaTeam");
        String result = ShowAllTeamBoardsCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, "nemaTeam");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void noBoardsTest() {
        Team team2 = new TeamImpl("otbor2");
        engine.getTeams().put(team2.getName(), team2);

        parameters = Collections.singletonList(team2.getName());
        String result = ShowAllTeamBoardsCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.EmptyBoardsErrorMessage, team2.getName());

        Assert.assertEquals(expected, result);
    }
}
