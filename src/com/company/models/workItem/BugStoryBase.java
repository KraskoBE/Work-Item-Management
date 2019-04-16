package com.company.models.workItem;

import com.company.engine.EngineConstants;
import com.company.models.common.Priority;
import com.company.models.common.Status;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.BugStory;
import com.company.models.unit.MemberImpl;

public abstract class BugStoryBase extends WorkItemBase implements BugStory {
    private static final Member noAssignee = new MemberImpl("No Assignee");
    private Priority priority;
    private Member assignee;

    BugStoryBase(int id, String title, String description, Status status, Priority priority) {
        super(id, title, description, status);
        setPriority(priority);
        setAssignee(noAssignee);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        addActivityHistory(String.format(EngineConstants.PrioritySet_WorkItemActivity, priority.toString()));
    }

    public Member getAssignee() {
        return assignee;
    }

    public void setAssignee(Member assignee) {
        this.assignee = assignee;
        addActivityHistory(String.format(EngineConstants.Assigned_WorkItemActivity, assignee.getName()));
    }

    public void removeAssignee() {
        assignee = noAssignee;
        addActivityHistory(EngineConstants.Unassigned_WorkItemActivity);
    }

}
