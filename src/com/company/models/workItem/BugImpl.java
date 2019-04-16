package com.company.models.workItem;

import com.company.engine.EngineConstants;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.contracts.workItem.Bug;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends BugStoryBase implements Bug {
    private List<String> stepsToReproduce;
    private Severity severity;

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Status status) {
        super(id, title, description, status, priority);
        setSeverity(severity);
        setStepsToReproduce();
        setHistory();
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
        addActivityHistory(String.format(EngineConstants.SeveritySet_WorkItemActivity, severity.toString()));
    }

    @Override
    public void setStatus(Status status) {

        if (status != Status.Active && status != Status.Fixed)
            throw new IllegalArgumentException(ERROR_INVALID_STATUS);
        this.status = status;
        addActivityHistory(String.format(EngineConstants.StatusSet_WorkItemActivity, status.toString()));
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    public Severity getSeverity() {
        return severity;
    }

    private void setStepsToReproduce() {
        this.stepsToReproduce = new ArrayList<>();
    }

    @Override
    String additionalInfo() {
        return String.format("Priority: %s\n" +
                        "   Severity: %s\n" +
                        "   Assignee: %s\n" +
                        "   Steps to reproduce: %s",
                getPriority().toString(),
                getSeverity().toString(),
                getAssignee().getName(),
                getStepsToReproduce().toString());
    }
}
