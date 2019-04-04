package com.company.engine;

import com.company.engine.contracts.Command;
import com.company.engine.contracts.Engine;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Feedback;
import com.company.models.contracts.workItem.Story;
import com.company.models.contracts.workItem.WorkItem;

import javax.swing.*;
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
        teams = new HashMap<>();
        members = new HashMap<>();
        boards = new HashMap<>();
        workItems = new HashMap<>();
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

    private String processCommand(Command command) {
        String commandResult;

        switch (command.getName()) {
            case EngineConstants.CreateMemberCommand:
                String memberName = command.getParameters().get(0);

                commandResult = createMember(memberName);
                break;
            case EngineConstants.CreateTeamCommand:
                String teamName = command.getParameters().get(0);

                commandResult = createTeam(teamName);
                break;
            case EngineConstants.CreateBoardCommand:
                String boardName = command.getParameters().get(0);
                teamName = command.getParameters().get(1);

                commandResult = createBoard(boardName, teamName);
                break;
            case EngineConstants.CreateBugCommand:
                String bugName = command.getParameters().get(0);
                String bugDescription = command.getParameters().get(1);
                String bugPriority = command.getParameters().get(2);
                String bugSeverity = command.getParameters().get(3);
                String bugBoard = command.getParameters().get(4);
                String bugTeam = command.getParameters().get(5);

                commandResult = createBug(bugName, bugDescription, bugPriority, bugSeverity, bugBoard, bugTeam);
                break;
            case EngineConstants.CreateStoryCommand:
                String storyName = command.getParameters().get(0);
                String storyDescription = command.getParameters().get(1);
                String storyStatus = command.getParameters().get(2);
                String storyPriority = command.getParameters().get(3);
                String storySize = command.getParameters().get(4);
                String storyBoard = command.getParameters().get(5);
                String storyTeam = command.getParameters().get(6);

                commandResult = createStory(storyName, storyDescription, storyStatus, storyPriority, storySize, storyBoard, storyTeam);
                break;
            case EngineConstants.CreateFeedbackCommand:
                String feedbackName = command.getParameters().get(0);
                String feedbackDescription = command.getParameters().get(1);
                String feedbackStatus = command.getParameters().get(2);
                int feedbackRating = Integer.parseInt(command.getParameters().get(3));
                String feedbackBoard = command.getParameters().get(4);
                String feedbackTeam = command.getParameters().get(5);

                commandResult = createFeedback(feedbackName, feedbackDescription, feedbackStatus, feedbackRating, feedbackBoard, feedbackTeam);
                break;
            case EngineConstants.ShowAllPeopleCommand:
                commandResult = showAllPeople();
                break;
            case EngineConstants.ShowAllTeamsCommand:
                commandResult = showAllTeams();
                break;
            case EngineConstants.ShowAllTeamMembersCommand:
                teamName = command.getParameters().get(0);

                commandResult = showAllTeamMembers(teamName);
                break;
            case EngineConstants.ShowAllTeamBoardsCommand:
                teamName = command.getParameters().get(0);

                commandResult = showAllTeamBoards(teamName);
                break;
            case EngineConstants.AddMemberToTeamCommand:
                memberName = command.getParameters().get(0);
                teamName = command.getParameters().get(1);

                commandResult = addMemberToTeam(memberName, teamName);
                break;
            case EngineConstants.ChangeCommand:
                int workItemID = Integer.parseInt(command.getParameters().get(0));
                String changeType = command.getParameters().get(1);
                String changeValue = command.getParameters().get(2);

                commandResult = changeCommand(workItemID, changeType, changeValue);
                break;
            default:
                commandResult = String.format(EngineConstants.InvalidCommandErrorMessage, command.getName());
                break;
        }
        return commandResult;
    }

    private String createMember(String name) {
        if (members.containsKey(name)) {
            return String.format(EngineConstants.MemberExistsErrorMessage, name);
        }
        Member member = factory.createMember(name);
        members.put(name, member);

        return String.format(EngineConstants.MemberCreatedSuccessMessage, name);
    }

    private String createTeam(String name) {
        if (teams.containsKey(name)) {
            return String.format(EngineConstants.TeamExistsErrorMessage, name);
        }
        Team team = factory.createTeam(name);
        teams.put(name, team);

        return String.format(EngineConstants.TeamCreatedSuccessMessage, name);

    }

    private String createBoard(String name, String teamName) {
        if (!teams.containsKey(teamName)) {
            return String.format(EngineConstants.TeamDoesNotExist, teamName);
        }
        Team tempTeam = teams.get(teamName);
        Map<String, Board> teamBoards = tempTeam.getBoards();

        if (teamBoards.containsKey(name))
            return String.format(EngineConstants.BoardExistsInTeamErrorMessage, name, teamName);

        Board board = factory.createBoard(name);
        tempTeam.getBoards().put(name, board);
        boards.put(name, board);
        return String.format(EngineConstants.BoardCreatedSuccessMessage, name);
    }

    private String createBug(String name, String description, String priority, String severity, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnTheTeam, board, team);

        Bug bug = factory.createBug(globalID, name, description, priority, severity);
        workItems.put(globalID, bug);

        teams.get(team).getBoards().get(board).addWorkItem(bug);
        teams.get(team).getBoards().get(board).addActivity(String.format(EngineConstants.AddedWorkItemToHistory, "bug", name, globalID));
        return String.format(EngineConstants.BugCreatedSuccessMessage, name, globalID++);
    }

    private String createStory(String name, String description, String status, String priority, String size, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnTheTeam, board, team);

        Story story = factory.createStory(globalID, name, description, status, priority, size);
        workItems.put(globalID, story);

        teams.get(team).getBoards().get(board).addWorkItem(story);
        teams.get(team).getBoards().get(board).addActivity(String.format(EngineConstants.AddedWorkItemToHistory, "story", name, globalID));
        return String.format(EngineConstants.StoryCreatedSuccessMessage, name, globalID++);
    }

    private String createFeedback(String name, String description, String status, int rating, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnTheTeam, board, team);

        Feedback feedback = factory.createFeedback(globalID, name, description, status, rating);
        workItems.put(globalID, feedback);

        teams.get(team).getBoards().get(board).addWorkItem(feedback);
        teams.get(team).getBoards().get(board).addActivity(String.format(EngineConstants.AddedWorkItemToHistory, "feedback", name, globalID));
        return String.format(EngineConstants.FeedBackSuccessMessage, name, globalID++);

    }

    private String showAllPeople() {
        if (members.isEmpty())
            return EngineConstants.NoMembersErrorMessage;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[PEOPLE]\n");
        members.forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }

    private String showAllTeams() {
        if (teams.isEmpty())
            return EngineConstants.NoTeamsErrorMessage;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[TEAMS]\n");
        teams.forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }

    private String showAllTeamMembers(String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);
        if (teams.get(team).getMembers().isEmpty())
            return String.format(EngineConstants.EmptyTeamErrorMessage, team);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[MEMBERS of %s]\n", team));
        teams.get(team).getMembers().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }

    private String showAllTeamBoards(String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);
        if (teams.get(team).getBoards().isEmpty())
            return String.format(EngineConstants.EmptyBoardsErrorMessage, team);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[BOARDS of %s]\n", team));
        teams.get(team).getBoards().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }

    private String changeCommand(int id, String type, String value) {
        if (!workItems.containsKey(id))
            return String.format(EngineConstants.WorkItemDoesNotExist, id);

        if (workItems.get(id) instanceof Bug) {
            switch (type.toLowerCase()) {
                case "priority":
                    ((Bug) workItems.get(id)).setPriority(FactoryImpl.getPriority(value));
                    break;
                case "severity":
                    ((Bug) workItems.get(id)).setSeverity(FactoryImpl.getSeverity(value));
                    break;
                case "status":
                    workItems.get(id).setStatus(FactoryImpl.getStatus(value));
                    break;
                default:
                    return String.format(EngineConstants.InvalidObjectType, type);
            }
        }

        if (workItems.get(id) instanceof Story) {
            switch (type.toLowerCase()) {
                case "priority":
                    ((Story) workItems.get(id)).setPriority(FactoryImpl.getPriority(value));
                    break;
                case "size":
                    ((Story) workItems.get(id)).setSize(FactoryImpl.getSize(value));
                    break;
                case "status":
                    workItems.get(id).setStatus(FactoryImpl.getStatus(value));
                    break;
                default:
                    return String.format(EngineConstants.InvalidObjectType, type);
            }
        }

        if (workItems.get(id) instanceof Feedback) {
            switch (type.toLowerCase()) {
                case "rating":
                    ((Feedback) workItems.get(id)).setRating(Integer.parseInt(value));
                    break;
                case "status":
                    workItems.get(id).setStatus(FactoryImpl.getStatus(value));
                    break;
                default:
                    return String.format(EngineConstants.InvalidObjectType, type);

            }
        }

        return String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, workItems.get(id).getTitle(), type, value);
    }

    private String addMemberToTeam(String memberName, String teamName) {
        if (!teams.containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExist, teamName);

        if (!members.containsKey(memberName))
            return String.format(EngineConstants.MemberDoesNotExist, memberName);

        if (teams.get(teamName).getMembers().containsKey(memberName))
            return String.format(EngineConstants.MemberAlreadyInTeam, memberName, teamName);

        teams.get(teamName).getMembers().put(memberName, members.get(memberName));
        members.get(memberName).addActivity(String.format(EngineConstants.MemberJoinedTeam, memberName, teamName));
        return String.format(EngineConstants.MemberAddedSuccessMessage, memberName, teamName);
    }

    private void renderCommandResults(List<String> output) {
        //renderer.output(output);
    }

}
