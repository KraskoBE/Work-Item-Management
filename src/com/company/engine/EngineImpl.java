package com.company.engine;

import com.company.engine.commands.*;
import com.company.engine.contracts.Command;
import com.company.engine.contracts.Engine;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.*;

import java.util.*;

public class EngineImpl implements Engine {

    private Factory factory;

    private int globalID = 0;

    private Map<String, Team> teams;
    private Map<String, Member> members;
    private Map<String, Board> boards;
    private Map<Integer, WorkItem> workItems;

    public EngineImpl() {
        factory = new FactoryImpl();
        teams = new LinkedHashMap<>();
        members = new LinkedHashMap<>();
        boards = new LinkedHashMap<>();
        workItems = new LinkedHashMap<>();
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String currentLine = scanner.nextLine();
        try {
            while (currentLine != null && !currentLine.equals("")) {
                Command command = CommandImpl.parse(currentLine);
                System.out.println(processCommand(command));
                currentLine = scanner.nextLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Map<String, Board> getBoards() {
        return boards;
    }

    public Map<Integer, WorkItem> getWorkItems() {
        return workItems;
    }

    public int getGlobalID() {
        return globalID;
    }

    public int getGlobalIDWithIncrease() {
        return globalID++;
    }

    private String processCommand(Command command) {
        String commandResult;
        switch (command.getName()) {
            case EngineConstants.CreatePersonCommand:
                return CreatePersonCmd.execute(this, factory, command.getParameters());

            case EngineConstants.CreateTeamCommand:
                return CreateTeamCmd.execute(this, factory, command.getParameters());

            case EngineConstants.CreateBoardCommand:
                return CreateBoardCmd.execute(this, factory, command.getParameters());

            case EngineConstants.CreateBugCommand:
                return CreateBugCmd.execute(this, factory, command.getParameters());

            case EngineConstants.CreateStoryCommand:
                return CreateStoryCmd.execute(this, factory, command.getParameters());

            case EngineConstants.CreateFeedbackCommand:
                return CreateFeedbackCmd.execute(this, factory, command.getParameters());

            case EngineConstants.ShowAllPeopleCommand:
                return ShowAllPeopleCmd.execute(this);

            case EngineConstants.ShowAllTeamsCommand:
                return ShowAllTeamsCmd.execute(this);

            case EngineConstants.ShowAllTeamMembersCommand:
                return ShowAllTeamMembersCmd.execute(this, command.getParameters());

            case EngineConstants.ShowAllTeamBoardsCommand:
                return ShowAllTeamBoardsCmd.execute(this, command.getParameters());

            case EngineConstants.AddPersonToTeamCommand:
                return AddPersonToTeamCmd.execute(this, command.getParameters());

            case EngineConstants.ChangeCommand:
                return ChangeCmd.execute(this, command.getParameters());

            case EngineConstants.AssignCommand:
                return AssignCmd.execute(this, command.getParameters());

            case EngineConstants.UnassignCommand:
                return UnassignCmd.execute(this, command.getParameters());

            case EngineConstants.ListWorkItemsCommand:
                return ListWorkItemsCmd.execute(this, command.getParameters());

            case EngineConstants.SortWorkItemsCommand:
                return SortWorkItemsCmd.execute(this, command.getParameters());

            case EngineConstants.ShowTeamActivityCommand:
                return ShowTeamActivity.execute(this, command.getParameters());

            case EngineConstants.AddCommentCommand:
                return AddCommentCmd.execute(this, command.getParameters());

            case EngineConstants.ShowBoardActivityCommand:
                return ShowUnitActivityCmd.execute(this, command.getParameters(), "board");

            case EngineConstants.ShowPersonActivityCommand:
                return ShowUnitActivityCmd.execute(this, command.getParameters(), "person");

            default:
                commandResult = String.format(EngineConstants.InvalidCommandErrorMessage, command.getName());
                break;
        }
        return commandResult;
    }
}
