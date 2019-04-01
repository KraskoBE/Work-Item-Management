package com.company.models.contracts.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.contracts.unit.Member;

public interface Bug extends WorkItem {

    String getStepsToReproduce();

    Priority getPriority();

    Severity getSeverity();

    Member getAsignee();
}
