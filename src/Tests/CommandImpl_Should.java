package Tests;

import com.company.engine.CommandImpl;
import com.company.engine.contracts.Command;
import org.junit.Assert;
import org.junit.Test;

public class CommandImpl_Should {

    @Test
    public void validCommandPassed() {
        Command command = CommandImpl.parse("CreatePerson krasen");
        Assert.assertEquals("krasen", command.getParameters().get(0));
        Assert.assertEquals("createperson", command.getName());

        Command commandWithNoParameters = CommandImpl.parse("ShowAllPeople");
        Assert.assertEquals("showallpeople", commandWithNoParameters.getName());
    }
}
