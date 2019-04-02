package com.company.models;


import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        setMembers();
        setBoards();
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setMembers() {
        this.members = new ArrayList<>();
    }

    private void setBoards() {
        this.boards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Board> getBoards() {
        return boards;
    }
}
