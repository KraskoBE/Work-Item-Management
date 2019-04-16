package com.company.models.contracts.workItem;


import com.company.models.common.Priority;
import com.company.models.contracts.unit.Member;

public interface BugStory extends WorkItem {

    Priority getPriority();

    void setPriority(Priority priority);

    Member getAssignee();


    void setAssignee(Member assignee);


    void removeAssignee();
}
