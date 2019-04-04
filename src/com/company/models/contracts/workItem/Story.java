package com.company.models.contracts.workItem;

import com.company.models.common.Priority;
import com.company.models.common.Size;
import com.company.models.contracts.unit.Member;

public interface Story extends WorkItem {

    Priority getPriority();

    Size getSize();

    Member getAssignee();

    void setPriority(Priority priority);

    void setSize(Size size);

}
