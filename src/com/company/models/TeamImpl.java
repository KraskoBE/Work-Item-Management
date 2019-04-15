package com.company.models;


import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.HashMap;
import java.util.Map;

public class TeamImpl implements Team {
    private String name;
    private Map<String, Member> members;
    private Map<String, Board> boards;

    public TeamImpl(String name) {
        setName(name);
        setMembers();
        setBoards();
    }

    public String getName() {
        return name;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Map<String, Board> getBoards() {
        return boards;
    }

    private void setName(String name) {
        if(name.isEmpty())
            throw new IllegalArgumentException("Team name should not be empty");
        this.name = name;
    }

    private void setMembers() {
        this.members = new HashMap<>();
    }

    private void setBoards() {
        this.boards = new HashMap<>();
    }
}
