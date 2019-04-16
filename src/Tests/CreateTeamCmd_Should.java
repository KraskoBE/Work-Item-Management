package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.engine.commands.CreatePersonCmd;
import com.company.engine.commands.CreateTeamCmd;
import com.company.models.TeamImpl;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTeamCmd_Should {
    private EngineImpl engine;
    private FactoryImpl factory;
    private List<String> parameters;
    private TeamImpl team;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        factory = new FactoryImpl();
        parameters = new ArrayList<>();
        team = new TeamImpl("otbor");
    }

    @Test
    public void createTeamWithValidParameters() {
        parameters.add("otbor");

        String result = CreateTeamCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.TeamCreatedSuccessMessage, "otbor");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b");

        String result = CreateTeamCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {
        String result = CreateTeamCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamExists() {
        engine.getTeams().put(team.getName(), team);
        parameters.add(team.getName());

        String result = CreateTeamCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.TeamExistsErrorMessage, team.getName());

        Assert.assertEquals(expected, result);
    }
}
