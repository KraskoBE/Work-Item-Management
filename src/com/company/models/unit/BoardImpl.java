package com.company.models.unit;


import com.company.models.contracts.unit.Board;

public class BoardImpl extends UnitBase implements Board {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 10;
    private static final String ERROR_NAME_LENGTH = "Error: name is less than %d or name is more than %d";

    public BoardImpl(String name) {
        super(name);
    }

    @Override
    void setName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH)
            throw new IllegalArgumentException(String.format(ERROR_NAME_LENGTH, NAME_MIN_LENGTH, NAME_MAX_LENGTH));

        this.name = name;
    }

}
