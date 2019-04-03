package com.company.engine;

import com.company.engine.contracts.Factory;
import com.company.models.TeamImpl;
import com.company.models.common.Priority;
import com.company.models.common.Severity;
import com.company.models.common.Status;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.unit.BoardImpl;
import com.company.models.unit.MemberImpl;
import com.company.models.workItem.BugImpl;

import java.awt.*;

public class FactoryImpl implements Factory {

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
    public Bug createBug(int id, String name, String description, String priority, String severity, String status) {
        Member member = new MemberImpl("krasen");
        return new BugImpl(id, name, description, getPriority(priority), getSeverity(severity), getStatus(status), member);
    }

    private Priority getPriority(String priority) {
        switch (priority.toLowerCase()) {
            case EngineConstants.HIGH:
                return Priority.High;
            case EngineConstants.MEDIUM:
                return Priority.Medium;
            case EngineConstants.LOW:
                return Priority.Low;
            default:
                throw new IllegalArgumentException(String.format(EngineConstants.INVALID_PRIORITY_NAME, priority));
        }
    }

    private Severity getSeverity(String severity) {
        switch (severity.toLowerCase()) {
            case EngineConstants.CRITICAL:
                return Severity.Critical;
            case EngineConstants.MAJOR:
                return Severity.Major;
            case EngineConstants.MINOR:
                return Severity.Minor;
            default:
                throw new IllegalArgumentException(String.format(EngineConstants.INVALID_SEVERITY_NAME, severity));
        }
    }

    private Status getStatus(String status) {
        switch (status.toLowerCase()) {
            case EngineConstants.ACTIVE:
                return Status.Active;
            case EngineConstants.FIXED:
                return Status.Fixed;
            case EngineConstants.NOTDONE:
                return Status.NotDone;
            case EngineConstants.INPROGRESS:
                return Status.InProgress;
            case EngineConstants.DONE:
                return Status.Done;
            case EngineConstants.NEW:
                return Status.New;
            case EngineConstants.UNSCHEDULED:
                return Status.Unscheduled;
            case EngineConstants.SCHEDULED:
                return Status.Scheduled;
            default:
                throw new IllegalArgumentException(String.format(EngineConstants.INVALID_STATUS_NAME, status));

        }
    }

}
