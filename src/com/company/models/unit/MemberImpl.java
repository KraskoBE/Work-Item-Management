package com.company.models.unit;


import com.company.engine.EngineConstants;
import com.company.models.contracts.unit.Member;

import java.util.ArrayList;

public class MemberImpl extends UnitBase implements Member {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;

    public MemberImpl(String name) {
        super(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH);
    }

    @Override
    void setActivityHistory() {
        this.activityHistory = new ArrayList<>();
        activityHistory.add(EngineConstants.PersonCreated_PersonActivity);
    }
}
