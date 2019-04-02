package com.company.models.unit;

import com.company.models.contracts.unit.Unit;
import com.company.models.workItem.WorkItemBase;

import java.util.ArrayList;
import java.util.List;

public abstract class UnitBase implements Unit {
    protected String name;
    private List<WorkItemBase> items;
    private List<String> activityHistory;

    public UnitBase(String name) {
        setName(name);
        setItems();
        setActivityHistory();
    }

    private void setItems() {
        this.items = new ArrayList<>();
    }

    private void setActivityHistory() {
        this.activityHistory = new ArrayList<>();
    }

    abstract void setName(String name);

    public String getName() {
        return name;
    }

    public List<WorkItemBase> getItems() {
        return new ArrayList<>(items);
    }

    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }
}
