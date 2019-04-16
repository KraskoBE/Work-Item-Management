package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowAllPeopleCmd;
import com.company.engine.commands.ShowAllTeamsCmd;
import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Member;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowAllTeamsCmd_Should {

    private EngineImpl engine;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
    }

    @Test
    public void ShowAllTeams() {
        Team team1 = new TeamImpl("otbor");
        Team team2 = new TeamImpl("superekip");

        engine.getTeams().put(team1.getName(), team1);
        engine.getTeams().put(team2.getName(), team2);

        String result = ShowAllTeamsCmd.execute(engine);
        String expected = "[TEAMS]\n" + String.join("\n", team1.getName(), team2.getName());
        Assert.assertEquals(expected, result);
    }
    @Test
    public void errorNoTeams() {
        String result = ShowAllTeamsCmd.execute(engine);
        String expected = EngineConstants.NoTeamsErrorMessage;

        Assert.assertEquals(expected, result);
    }
}
