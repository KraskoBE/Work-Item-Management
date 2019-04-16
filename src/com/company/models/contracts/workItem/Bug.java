package com.company.models.contracts.workItem;

import com.company.models.common.Severity;
import com.company.models.common.Status;

import java.util.List;

public interface Bug extends BugStory {

    void setSeverity(Severity severity);

    void setStatus(Status status);

    List<String> getStepsToReproduce();

    Severity getSeverity();
}
