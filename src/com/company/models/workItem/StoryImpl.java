package com.company.models.workItem;

import com.company.engine.EngineConstants;
import com.company.models.common.Priority;
import com.company.models.common.Size;
import com.company.models.common.Status;
import com.company.models.contracts.workItem.Story;

public class StoryImpl extends BugStoryBase implements Story {
    private Size size;

    public StoryImpl(int id, String title, String description, Priority priority, Size size) {
        super(id, title, description, Status.InProgress, priority);
        setSize(size);
        setHistory();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public void setStatus(Status status) {
        if (status != Status.NotDone && status != Status.InProgress && status != Status.Done)
            throw new IllegalArgumentException(ERROR_INVALID_STATUS);
        this.status = status;
        addActivityHistory(String.format(EngineConstants.StatusSet_WorkItemActivity, status.toString()));
    }

    @Override
    String additionalInfo() {
        return String.format("Priority: %s\n" +
                        "   Size: %s\n" +
                        "   Assignee: %s",
                getPriority().toString(),
                getSize().toString(),
                getAssignee().getName());
    }
}
