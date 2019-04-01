package com.company.models.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;

public class BugImpl extends WorkItemBase implements Bug {
    private String stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Member asignee;

    public BugImpl(int id, String title, String description, Status status, String stepsToReproduce, Priority priority, Severity severity, Member asignee) {
        super(id, title, description, status);
        setPriority(priority);
        setSeverity(severity);
        setStepsToReproduce(stepsToReproduce);
        setAsignee(asignee);
    }

    private void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSeverity(Severity severity) {
        this.severity = severity;
    }

    private void setAsignee(Member asignee) {
        this.asignee = asignee;
    }

    @Override
    void setStatus(Status status) {
        if (status != Status.Active && status != Status.Fixed)
            throw new IllegalArgumentException();
        this.status = status;
    }


    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public Priority getPriority() {
        return priority;
    }

    public Severity getSeverity() {
        return severity;
    }

    public Member getAsignee() {
        return asignee;
    }

}
