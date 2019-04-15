package Tests;

import com.company.models.TeamImpl;
import org.junit.Test;

public class Team_Should {
    @Test
    public void createTeamWhenValidParametersArePassed() {
        TeamImpl team = new TeamImpl("name");
    }
}
