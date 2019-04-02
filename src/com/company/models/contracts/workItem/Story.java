package com.company.models.contracts.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Size;
import com.company.models.contracts.unit.Member;

public interface Story extends WorkItem{

    public Priority getPriority();

    public Size getSize();

    public Member getAssignee();

}
