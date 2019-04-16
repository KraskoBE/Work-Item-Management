package com.company.models.contracts.workItem;

import com.company.models.common.Size;

public interface Story extends BugStory {

    Size getSize();

    void setSize(Size size);
}
