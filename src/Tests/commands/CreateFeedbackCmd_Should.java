package Tests.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.engine.commands.CreateBugCmd;
import com.company.engine.commands.CreateFeedbackCmd;
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

public class CreateFeedbackCmd_Should {
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
    public void createFeedbackWithValidParameters() {
        parameters = Arrays.asList("ImeNaFeedback", "description na bug", String.valueOf(100), "board", "otbor");

        String result = CreateFeedbackCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.FeedBackSuccessMessage, "ImeNaFeedback", 0);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamDoesNotExist() {
        parameters = Arrays.asList("ImeNaFeedback", "description na bug", String.valueOf(100), "board", "nemaOtbor");

        String result = CreateFeedbackCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, "nemaOtbor");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenBoardIsNotOnTheTeam() {
        parameters = Arrays.asList("ImeNaFeedback", "description na bug", String.valueOf(100), "nemaBoard", "otbor");

        String result = CreateFeedbackCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, "nemaBoard", team.getName());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c", "d");

        String result = CreateFeedbackCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        String result = CreateFeedbackCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }
}