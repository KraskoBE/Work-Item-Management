package com.company.models.unit;


import com.company.engine.EngineConstants;
import com.company.models.contracts.unit.Board;

import java.util.ArrayList;

public class BoardImpl extends UnitBase implements Board {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 10;

    public BoardImpl(String name) {
        super(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH);
    }

    @Override
    void setActivityHistory() {
        this.activityHistory = new ArrayList<>();
        activityHistory.add(EngineConstants.BoardCreated_BoardActivity);
    }
}
