package Tests.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowTeamActivityCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShowTeamActivityCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Team team;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        team = new TeamImpl("otbor");
        engine.getTeams().put(team.getName(), team);
    }

    @Test
    public void showActivityTest() {
        parameters = Collections.singletonList(team.getName());
        team.addActivity("new activity");

        String result = ShowTeamActivityCmd.execute(engine, parameters);
        String expected = String.format("[%s activity]", team.getName())
                + "\nTeam created"
                + "\nnew activity";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamDoesNotExist() {
        parameters = Collections.singletonList("nemaOtbor");

        String result = ShowTeamActivityCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, "nemaOtbor");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenInvalidParameters() {
        String result = ShowTeamActivityCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("a", "b");
        result = ShowTeamActivityCmd.execute(engine, parameters);
        expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);
    }
}
