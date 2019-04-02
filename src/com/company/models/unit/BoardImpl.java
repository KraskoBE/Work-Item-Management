package com.company.models.unit;


import com.company.models.contracts.unit.Board;

public class BoardImpl extends UnitBase implements Board {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 10;

    public BoardImpl(String name) {
        super(name);
    }

    @Override
    void setName(String name) {
        if(name.length() < NAME_MIN_LENGTH ||  name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

}
