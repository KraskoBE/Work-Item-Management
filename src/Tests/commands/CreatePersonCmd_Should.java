package Tests.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.FactoryImpl;
import com.company.engine.commands.CreateBoardCmd;
import com.company.engine.commands.CreatePersonCmd;
import com.company.models.contracts.unit.Member;
import com.company.models.unit.MemberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreatePersonCmd_Should {
    private EngineImpl engine;
    private FactoryImpl factory;
    private List<String> parameters;
    private Member member;

    @Before
    public void beforeFunc() {
        engine = new EngineImpl();
        factory = new FactoryImpl();
        parameters = new ArrayList<>();
        member = new MemberImpl("krasen");
    }

    @Test
    public void createPersonWithValidParameters() {
        parameters.add("krasen");

        String result = CreatePersonCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.PersonCreatedSuccessMessage, "krasen");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenMoreParametersArePassed() {
        parameters = Arrays.asList("a", "b");

        String result = CreatePersonCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
    public void errorWhenLessParametersArePassed() {

        String result = CreatePersonCmd.execute(engine, factory, parameters);
        String expected = EngineConstants.InvalidNumberOfParameters;

        Assert.assertEquals(expected, result);
    }

    @Test
     public void errorWhenPersonExists() {
        engine.getMembers().put(member.getName(), member);
        parameters.add(member.getName());

        String result = CreatePersonCmd.execute(engine, factory, parameters);
        String expected = String.format(EngineConstants.PersonExistsErrorMessage, member.getName());

        Assert.assertEquals(expected, result);

     }
}
