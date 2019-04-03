package com.company.engine.contracts;

import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;

public interface Factory {

    Team createTeam(String name);

    Member createMember(String name);

    Board createBoard(String name);

    Bug createBug(int id, String name, String description, String priority, String severity, String Status, Member assignee);
}
