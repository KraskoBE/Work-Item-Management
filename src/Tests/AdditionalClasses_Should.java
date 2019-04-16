package Tests;

import com.company.engine.FactoryImpl;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Size;
import com.company.models.common.Status;
import org.junit.Assert;
import org.junit.Test;

public class AdditionalClasses_Should {

    @Test
    public void enumToString() {
        Assert.assertEquals(Priority.Low, FactoryImpl.getPriority("low"));
        Assert.assertEquals(Severity.Minor, FactoryImpl.getSeverity("minor"));
        Assert.assertEquals(Status.InProgress, FactoryImpl.getStatus("inprogress"));
        Assert.assertEquals(Status.Scheduled, FactoryImpl.getStatus("scheduled"));
        Assert.assertEquals(Size.Medium, FactoryImpl.getSize("medium"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumPriorityWrong() {
        FactoryImpl.getPriority("invalid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumSeverityWrong() {
        FactoryImpl.getSeverity("invalid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumStatusWrong() {
        FactoryImpl.getStatus("invalid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumSizeWrong() {
        FactoryImpl.getSize("invalid");
    }
}
