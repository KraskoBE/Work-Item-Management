package com.company.engine.contracts;

import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;

public interface Factory {

    Team createTeam(String name);

    Member createMember(String name);

    Board createBoard(String name);

    Bug createBug(int id, String name, String description, String priority, String severity, String Status);

    Story createStory(int id, String name, String description, String status, String priority, String size);

    Feedback createFeedback(int id, String name, String description, String status, int rating);
}
