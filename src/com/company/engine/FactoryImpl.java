package com.company.engine;

import com.company.engine.contracts.Factory;
import com.company.models.TeamImpl;
import com.company.models.common.Priority;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.unit.BoardImpl;
import com.company.models.unit.MemberImpl;
import com.company.models.workItem.BugImpl;

public class FactoryImpl implements Factory {

    private static final String HIGH = "high";
    private static final String MEDIUM = "medium";
    private static final String LOW = "low";
    private static final String INVALID_PRIORITY_NAME = "Invalid priority name: %s";


    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public Member createMember(String name) {
        return new MemberImpl(name);
    }

    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public Bug createBug(String name, String description, String priority, String severity, String Status, String assignee) {
        //return new BugImpl(name,description, getPriority(priority),)
        return null;
    }

    private Priority getPriority(String priority)
    {
        switch (priority.toLowerCase())
        {
            case HIGH:
                return Priority.High;
            case MEDIUM:
                return Priority.Medium;
            case LOW:
                return Priority.Low;
            default:
                throw new IllegalArgumentException(String.format(INVALID_PRIORITY_NAME, priority));
        }
    }
}
