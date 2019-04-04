package com.company.models.unit;

import com.company.models.contracts.unit.Unit;
import com.company.models.contracts.workItem.WorkItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UnitBase implements Unit {
    String name;
    private Map<String, WorkItem> items;
    List<String> activityHistory;

    UnitBase(String name) {
        setName(name);
        setItems();
        setActivityHistory();
    }

    private void setItems() {
        this.items = new HashMap<>();
    }

    abstract void setActivityHistory();

    abstract void setName(String name);

    public String getName() {
        return name;
    }

    public Map<String, WorkItem> getItems() {
        return new HashMap<>(items);
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addWorkItem(WorkItem workItem) {
        this.items.put(workItem.getTitle(), workItem);
    }

    @Override
    public void addActivity(String activity) {
        this.activityHistory.add(activity);
    }
}
