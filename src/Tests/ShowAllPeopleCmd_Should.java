package Tests;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.commands.ShowAllPeopleCmd;
import com.company.models.contracts.unit.Member;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ShowAllPeopleCmd_Should {
    private EngineImpl engine;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
    }

    @Test
    public void ShowAllPeople() {
        Member member1 = new MemberImpl("krasen");
        Member member2 = new MemberImpl("stoyan");

        engine.getMembers().put(member1.getName(), member1);
        engine.getMembers().put(member2.getName(), member2);

        String result = ShowAllPeopleCmd.execute(engine);
        String expected = "[PEOPLE]\n" + String.join("\n", member1.getName(), member2.getName());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorNoMembers() {
        String result = ShowAllPeopleCmd.execute(engine);
        String expected = EngineConstants.NoMembersErrorMessage;

        Assert.assertEquals(expected, result);
    }
}
