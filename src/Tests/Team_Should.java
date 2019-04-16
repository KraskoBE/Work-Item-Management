package Tests;

import com.company.models.TeamImpl;
import com.company.models.contracts.Team;
import org.junit.Test;

public class Team_Should {
    @Test
    public void createTeamWhenValidParametersArePassed() {
        Team team = new TeamImpl("otbor");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsEmpty() {
        Team team = new TeamImpl("");
    }
}
