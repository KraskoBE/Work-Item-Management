package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowAllPeopleCmd;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ShowAllPeopleCmd_Should {
    private EngineImpl engine;
    private MemberImpl member;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        member = new MemberImpl("krasen");
    }

    @Test
    public void ShowAllPeople() {
        engine.getMembers().put(member.getName(), member);


        String result = ShowAllPeopleCmd.execute(engine);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[PEOPLE]\n");
        engine.getMembers().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        String expected = stringBuilder.toString().trim();

        Assert.assertEquals(expected, result);

    }

    @Test
    public void errorNoMembers() {

        String result = ShowAllPeopleCmd.execute(engine);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[PEOPLE]\n");
        engine.getMembers().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        String expected = EngineConstants.NoMembersErrorMessage;

        Assert.assertEquals(expected, result);

    }


}
