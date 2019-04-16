package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowAllTeamMembersCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Member;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShowAllTeamMembersCmd_Should {
    private EngineImpl engine;
    private List<String> parameters;
    private Team team;
    private Member member1;
    private Member member2;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        parameters = new ArrayList<>();
        team = new TeamImpl("otbor");
        member1 = new MemberImpl("krasen");
        member2 = new MemberImpl("stoyan");
        team.getMembers().put(member1.getName(), member1);
        team.getMembers().put(member2.getName(), member2);
        engine.getTeams().put(team.getName(), team);
        engine.getMembers().put(member1.getName(), member1);
        engine.getMembers().put(member2.getName(), member2);
    }

    @Test
    public void showTeamMembersTest() {
        parameters = Collections.singletonList(team.getName());

        String result = ShowAllTeamMembersCmd.execute(engine, parameters);
        String expected = String.format("[MEMBERS of %s]\n", team.getName()) +
                member1.getName() + "\n" +
                member2.getName();

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenTeamDoesNotExist() {
        parameters = Collections.singletonList("nemaTeam");

        String result = ShowAllTeamMembersCmd.execute(engine, parameters);
        String expected = String.format(EngineConstants.TeamDoesNotExistErrorMessage, "nemaTeam");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorEmptyTeam() {
        Team team1 = new TeamImpl("otbor2");
        engine.getTeams().put(team1.getName(), team1);
        parameters = Collections.singletonList(team1.getName());

        String result = ShowAllTeamMembersCmd.execute(engine, parameters);
        String expected = EngineConstants.NoMembersErrorMessage;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenInvalidParameters() {
        String result = ShowAllTeamMembersCmd.execute(engine, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);

        parameters = Arrays.asList("a", "b");
        result = ShowAllTeamMembersCmd.execute(engine, parameters);
        expected = EngineConstants.InvalidNumberOfParameters;
        Assert.assertEquals(expected, result);
    }
}
