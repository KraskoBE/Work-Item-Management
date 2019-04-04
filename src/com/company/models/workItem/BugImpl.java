package com.company.models.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends WorkItemBase implements Bug {
    private static final String ERROR_INVALID_STATUS = "Error: invalid status";
    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Member assignee;

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Status status) {
        super(id, title, description, status);
        setPriority(priority);
        setSeverity(severity);
        setStepsToReproduce();
    }

    private void setStepsToReproduce() {
        this.stepsToReproduce = new ArrayList<>();
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    @Override
    public void setStatus(Status status) {
        if (status != Status.Active && status != Status.Fixed)
            throw new IllegalArgumentException(ERROR_INVALID_STATUS);
        this.status = status;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    public Priority getPriority() {
        return priority;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Member getAssignee() {
        return assignee;
    }

}
