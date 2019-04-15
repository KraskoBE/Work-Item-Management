package com.company.models.contracts.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.contracts.unit.Member;

import java.util.List;

public interface Bug extends BugStory {

    List<String> getStepsToReproduce();

    Priority getPriority();

    Severity getSeverity();

    Member getAssignee();

    void setPriority(Priority priority);

    void setSeverity(Severity severity);
}
