package com.company.models.contracts.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.contracts.unit.Member;

import java.util.List;

public interface Bug extends WorkItem {

    List<String> getStepsToReproduce();

    Priority getPriority();

    Severity getSeverity();

    Member getAssignee();
}
