package com.company.models.unit;

import com.company.models.contracts.unit.Unit;
import com.company.models.contracts.workItem.WorkItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class UnitBase implements Unit {
    private static final String ERROR_NAME_LENGTH = "Name should be %d-%d characters";

    private String name;
    private Map<Integer, WorkItem> items;
    List<String> activityHistory;

    UnitBase(String name, int NAME_MIN_LENGTH, int NAME_MAX_LENGTH) {
        setName(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH);
        setItems();
        setActivityHistory();
    }

    public String getName() {
        return name;
    }

    public Map<Integer, WorkItem> getItems() {
        return new LinkedHashMap<>(items);
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addWorkItem(WorkItem workItem) {
        this.items.put(workItem.getId(), workItem);
        addActivity(String.format("Work item with ID:%d added", workItem.getId()));
    }

    @Override
    public void addActivity(String activity) {
        this.activityHistory.add(activity);
    }

    public void removeWorkItem(int id) {
        this.items.remove(id);
        addActivity(String.format("Work item with ID:%d removed", id));
    }

    abstract void setActivityHistory();

    private void setItems() {
        this.items = new LinkedHashMap<>();
    }

    private void setName(String name, int NAME_MIN_LENGTH, int NAME_MAX_LENGTH) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH)
            throw new IllegalArgumentException(String.format(ERROR_NAME_LENGTH, NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }
}
