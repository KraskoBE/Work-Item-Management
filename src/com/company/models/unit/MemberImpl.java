package com.company.models.unit;


import com.company.models.contracts.unit.Member;

import java.util.ArrayList;

public class MemberImpl extends UnitBase implements Member {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String ERROR_NAME_LENGTH = "Error: name is less than %d or name is more than %d";

    public MemberImpl(String name) {
        super(name);
    }

    @Override
    void setActivityHistory() {
        this.activityHistory = new ArrayList<>();
        activityHistory.add("Person created");
    }

    @Override
    void setName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH)
            throw new IllegalArgumentException(String.format(ERROR_NAME_LENGTH, NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }
}
