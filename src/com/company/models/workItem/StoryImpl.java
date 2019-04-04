package com.company.models.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Size;
import com.company.models.common.Status;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Story;

public class StoryImpl extends WorkItemBase implements Story {

    private static final String ERROR_INVALID_STATUS = "Error: invalid status";
    private Priority priority;
    private Size size;
    private Member assignee;

    public StoryImpl(int id, String title, String description, Status status, Priority priority, Size size) {
        super(id, title, description, status);
        setPriority(priority);
        setSize(size);
        setStatus(status);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Member getAssignee() {
        return assignee;
    }

    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    @Override
    public void setStatus(Status status) {
        if (status != Status.NotDone && status != Status.InProgress && status != Status.Done)
            throw new IllegalArgumentException(ERROR_INVALID_STATUS);
        this.status = status;
    }
}
