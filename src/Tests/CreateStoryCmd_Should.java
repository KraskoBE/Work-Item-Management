package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.engine.commands.CreateBugCmd;
import com.company.engine.commands.CreateStoryCmd;
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

public class CreateStoryCmd_Should {
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

        team.getBoards().put(board.getName(), board);
        engine.getBoards().put(board.getName(), board);
        engine.getTeams().put(team.getName(), team);
    }

    @Test
    public void createStoryWithValidParameters() {
        parameters = Arrays.asList("ImeNaStorry", "description na story", "High", "Large", "board", "otbor");

        String result = CreateStoryCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.StoryCreatedSuccessMessage, "ImeNaStorry", 0);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        String result = CreateStoryCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c", "d", "e");

        String result = CreateStoryCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamDoesNotExist() {
        parameters = Arrays.asList("ImeNaStorry", "description na story", "High", "Large", "board", "nemaOtbor");

        String result = CreateStoryCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, "nemaOtbor");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenBoardIsNotOnTheTeam() {
        parameters = Arrays.asList("ImeNaStorry", "description na story", "High", "Large", "nemaBoard", "otbor");

        String result = CreateStoryCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, "nemaBoard", team.getName());

        Assert.assertEquals(expected, result);
    }
}
