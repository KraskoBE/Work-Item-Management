package com.company.engine.contracts;

import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.WorkItem;

import java.util.Map;

public interface Engine {

    void start();

    Map<String, Team> getTeams();

    Map<String, Member> getMembers();

    Map<String, Board> getBoards();

    Map<Integer, WorkItem> getWorkItems();

    int getGlobalID();

    int getGlobalIDWithIncrease();
}
