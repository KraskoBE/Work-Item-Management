package com.company.models;


import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TeamImpl implements Team {
    private String name;
    private Map<String, Member> members;
    private Map<String, Board> boards;
    private List<String> activity;

    public TeamImpl(String name) {
        setName(name);
        setMembers();
        setBoards();
        setHistory();
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

    public List<String> getActivity() {
        return new ArrayList<>(activity);
    }

    public void addActivity(String message)
    {
        this.activity.add(message);
    }

    private void setName(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Team name should not be empty");
        this.name = name;
    }

    private void setMembers() {
        this.members = new LinkedHashMap<>();
    }

    private void setBoards() {
        this.boards = new LinkedHashMap<>();
    }

    private void setHistory() {
        this.activity = new ArrayList<>();
        activity.add("Team created");
    }
}
