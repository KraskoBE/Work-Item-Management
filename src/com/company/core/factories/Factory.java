package com.company.core.factories;

import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Size;
import com.company.models.common.Status;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;

public interface Factory {
    Member createMember(String name);

    Team createTeam(String name);

    Board createBoard(String boardName, String teamName);

    Bug createBug(String title, String description, Status status, Priority priority, Severity severity, Member assignee, Board board);

    Story createStory(String title, String description, Status status, Priority priority, Size size, Member assignee, Board board);

    Feedback createFeedback(String title, String description, Status status, int rating, Board board);
}
