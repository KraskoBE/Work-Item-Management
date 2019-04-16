package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.engine.commands.AddCommentCmd;
import com.company.engine.commands.CreateBoardCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.unit.BoardImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateBoardCmd_Should {
    private EngineImpl engine;
    private FactoryImpl factory;
    private List<String> parameters;
    private Team team;
    private Board board;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        factory = new FactoryImpl();
        parameters = new ArrayList<>();
        team = new TeamImpl("otbor");
        board = new BoardImpl("board");
    }

    @Test
    public void createBoardWithValidParameters() {
        engine.getTeams().put(team.getName(), team);

        parameters = Arrays.asList(board.getName(), team.getName());

        String result = CreateBoardCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.BoardCreatedSuccessMessage, board.getName());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c");

        String result = CreateBoardCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {
        parameters.add("a");

        String result = CreateBoardCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenBoardExistsInTeam() {
        engine.getTeams().put(team.getName(), team);
        engine.getBoards().put(board.getName(), board);

        parameters = Arrays.asList(board.getName(), team.getName());

        String result = CreateBoardCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.BoardExistsInTeamErrorMessage, board.getName(), team.getName());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamDoesNotExist() {

        parameters = Arrays.asList(board.getName(), team.getName());

        String result = CreateBoardCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, team.getName());

        Assert.assertEquals(expected, result);
    }
}
