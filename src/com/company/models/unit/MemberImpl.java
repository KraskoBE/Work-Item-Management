package com.company.models.unit;


import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.WorkItem;

public class MemberImpl extends UnitBase implements Member {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;

    public MemberImpl(String name) {
        super(name);
    }

    @Override
    void setName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH)
            throw new IllegalArgumentException();
        this.name = name;
    }
}
