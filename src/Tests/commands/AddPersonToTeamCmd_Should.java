package Tests.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.AddCommentCmd;
import com.company.engine.commands.AddPersonToTeamCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Member;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddPersonToTeamCmd_Should {

    private EngineImpl engine;
    private List<String> parameters;
    private Member member;
    private Team team;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        member = new MemberImpl("krasen");
        team = new TeamImpl("otbor");

    }

    @Test
    public void addPersonToTeamWithValidParameters() {
        engine.getTeams().put(team.getName(), team);
        engine.getMembers().put(member.getName(), member);

        parameters = Arrays.asList(member.getName(), team.getName());

        String result = AddPersonToTeamCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.PersonAddedSuccessMessage, member.getName(), team.getName());

        Assert.assertEquals(expected, result);

    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b", "c");

        String result = AddPersonToTeamCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {
        parameters.add("a");

        String result = AddPersonToTeamCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamDoesNotExist() {

        parameters = Arrays.asList(member.getName(), "EkipNomerEdno");

        String result = AddPersonToTeamCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, "EkipNomerEdno");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenPersonDoesNotExist() {
        engine.getTeams().put(team.getName(), team);

        parameters = Arrays.asList("stoyan", team.getName());

        String result = AddPersonToTeamCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.PersonDoesNotExistErrorMessage, "stoyan");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenPersonAlreadyInTeam() {
        engine.getTeams().put(team.getName(), team);
        engine.getMembers().put(member.getName(), member);
        team.getMembers().put(member.getName(), member);

        parameters = Arrays.asList(member.getName(), team.getName());

        String result = AddPersonToTeamCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.PersonAlreadyInTeamErrorMessage, member.getName(), team.getName());

        Assert.assertEquals(expected, result);
    }

}
