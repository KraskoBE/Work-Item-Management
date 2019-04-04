package com.company.models.contracts.unit;

import com.company.models.contracts.workItem.WorkItem;
import com.company.models.workItem.WorkItemBase;


import java.util.List;
import java.util.Map;

public interface Unit {
    String getName();

    Map<String, WorkItem> getItems();

    List<String> getActivityHistory();

    void addWorkItem(WorkItem workItem);

    void addActivity(String activity);
}
