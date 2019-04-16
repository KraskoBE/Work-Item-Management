package com.company.models.contracts.unit;

import com.company.models.contracts.workItem.WorkItem;

import java.util.List;
import java.util.Map;

public interface Unit {

    String getName();

    Map<Integer, WorkItem> getItems();

    List<String> getActivityHistory();

    void addWorkItem(WorkItem workItem);

    void addActivity(String activity);

    void removeWorkItem(int id);
}
