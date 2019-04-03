package com.company.models.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends WorkItemBase implements Bug {
    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Member assignee;

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Status status, Member assignee) {
        super(id, title, description, status);
        setPriority(priority);
        setSeverity(severity);
        setStepsToReproduce();
        setAssignee(assignee);
    }

    private void setStepsToReproduce() {
        this.stepsToReproduce = new ArrayList<>();
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    private void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    @Override
    void setStatus(Status status) {
        if (status != Status.Active && status != Status.Fixed)
            throw new IllegalArgumentException();
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
