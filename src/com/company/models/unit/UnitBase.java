package com.company.models.unit;

import com.company.models.contracts.unit.Unit;
import com.company.models.workItem.WorkItemBase;

import java.util.List;

public abstract class UnitBase implements Unit {
    private String name;
    private List<WorkItemBase> items;
    private List<String> activityHistory;

}
