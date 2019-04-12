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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public int getGlobalIDWithIncrease()
    {
        return globalID++;
    }

    private String processCommand(Command command) {
        String commandResult;

        switch (command.getName()) {
            case EngineConstants.CreateMemberCommand:
                return CreateMemberCmd.execute(this, factory, command.getParameters());
            case EngineConstants.CreateTeamCommand:
                return CreateTeamCmd.execute(this, factory, command.getParameters());
            case EngineConstants.CreateBoardCommand:
             return CreateBoardCmd.execute(this,factory, command.getParameters());
            case EngineConstants.CreateBugCommand:
                return CreateBugCmd.execute(this,factory,command.getParameters());
            case EngineConstants.CreateStoryCommand:
             return CreateStoryCmd.execute(this,factory,command.getParameters());
            case EngineConstants.CreateFeedbackCommand:
              return CreateFeedbackCmd.execute(this,factory,command.getParameters());
            case EngineConstants.ShowAllPeopleCommand:
               return ShowAllPeopleCmd.showAllPeopleCmd(this);
            case EngineConstants.ShowAllTeamsCommand:
                commandResult = showAllTeams();
                break;
            case EngineConstants.ShowAllTeamMembersCommand:
                String teamName = command.getParameters().get(0);

                commandResult = showAllTeamMembers(teamName);
                break;
            case EngineConstants.ShowAllTeamBoardsCommand:
                teamName = command.getParameters().get(0);

                commandResult = showAllTeamBoards(teamName);
                break;
            case EngineConstants.AddMemberToTeamCommand:
                String memberName = command.getParameters().get(0);
                teamName = command.getParameters().get(1);

                commandResult = addMemberToTeam(memberName, teamName);
                break;
            case EngineConstants.ChangeCommand:
                int workItemID = Integer.parseInt(command.getParameters().get(0));
                String changeType = command.getParameters().get(1);
                String changeValue = command.getParameters().get(2);

                commandResult = changeCommand(workItemID, changeType, changeValue);
                break;
            case EngineConstants.AssignCommand:
                workItemID = Integer.parseInt(command.getParameters().get(0));
                String assignee = command.getParameters().get(1);

                commandResult = assignCommand(workItemID, assignee);
                break;
            case EngineConstants.UnassignCommand:
                workItemID = Integer.parseInt(command.getParameters().get(0));
                assignee = command.getParameters().get(1);

                commandResult = unAssignCommand(workItemID, assignee);
                break;
            case EngineConstants.ListWorkItems:
                String firstParam = command.getParameters().get(0);
                if (command.getParameters().size() == 1) {
                    commandResult = listWorkItemsByCategory(firstParam);
                    break;
                }

                String secondParam = command.getParameters().get(1);
                if (firstParam.equals("sort")) {
                    commandResult = sortWorkItems(secondParam);
                    break;
                }
                /*if (firstParam.equals("status")) {
                    //commandResult = listWorkItemsByStatus(secondParam);
                }*/
                commandResult = "error";
                break;
            default:
                commandResult = String.format(EngineConstants.InvalidCommandErrorMessage, command.getName());
                break;
        }
        return commandResult;
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
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);
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
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, board, team);

        Bug bug = factory.createBug(globalID, name, description, priority, severity);
        workItems.put(globalID, bug);

        teams.get(team).getBoards().get(board).addWorkItem(bug);
        teams.get(team).getBoards().get(board).addActivity(String.format(EngineConstants.AddedWorkItemToHistory, "bug", name, globalID));
        return String.format(EngineConstants.BugCreatedSuccessMessage, name, globalID++);
    }

    private String createStory(String name, String description, String priority, String size, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, board, team);

        Story story = factory.createStory(globalID, name, description, priority, size);
        workItems.put(globalID, story);

        teams.get(team).getBoards().get(board).addWorkItem(story);
        teams.get(team).getBoards().get(board).addActivity(String.format(EngineConstants.AddedWorkItemToHistory, "story", name, globalID));
        return String.format(EngineConstants.StoryCreatedSuccessMessage, name, globalID++);
    }

    private String createFeedback(String name, String description, String status, int rating, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, board, team);

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
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, team);
        if (teams.get(team).getMembers().isEmpty())
            return String.format(EngineConstants.EmptyTeamErrorMessage, team);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[MEMBERS of %s]\n", team));
        teams.get(team).getMembers().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }

    private String showAllTeamBoards(String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, team);
        if (teams.get(team).getBoards().isEmpty())
            return String.format(EngineConstants.EmptyBoardsErrorMessage, team);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[BOARDS of %s]\n", team));
        teams.get(team).getBoards().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }

    private String changeCommand(int id, String type, String value) {
        if (!workItems.containsKey(id))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, id);

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
                    return String.format(EngineConstants.InvalidObjectTypeErrorMessage, type);
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
                    return String.format(EngineConstants.InvalidObjectTypeErrorMessage, type);
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
                    return String.format(EngineConstants.InvalidObjectTypeErrorMessage, type);
            }
        }

        return String.format(EngineConstants.WorkItemObjectChangedSuccessMessage, workItems.get(id).getTitle(), type, value);
    }

    private String addMemberToTeam(String memberName, String teamName) {
        if (!teams.containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);

        if (!members.containsKey(memberName))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, memberName);

        if (teams.get(teamName).getMembers().containsKey(memberName))
            return String.format(EngineConstants.MemberAlreadyInTeamErrorMessage, memberName, teamName);

        teams.get(teamName).getMembers().put(memberName, members.get(memberName));
        members.get(memberName).addActivity(String.format(EngineConstants.MemberJoinedTeam, memberName, teamName));
        return String.format(EngineConstants.MemberAddedSuccessMessage, memberName, teamName);
    }

    private String assignCommand(int id, String assignee) {
        if (!workItems.containsKey(id))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, id);
        if (!members.containsKey(assignee))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, assignee);
        if (!getMemberTeam(assignee).equals(getWorkItemTeam(id)))
            return String.format(EngineConstants.MemberIsNotFromTeamErrorMessage, assignee, getWorkItemTeam(id));
        if (members.get(assignee).getItems().containsKey(id))
            return String.format(EngineConstants.ItemAlreadyAssignedErrorMessage, assignee);

        if (workItems.get(id) instanceof Bug) {
            if (((Bug) workItems.get(id)).getAssignee() != null)
                return String.format(EngineConstants.ItemAlreadyAssignedErrorMessage, ((Bug) workItems.get(id)).getAssignee().getName());
            ((Bug) workItems.get(id)).setAssignee(members.get(assignee));
        }

        members.get(assignee).addWorkItem(workItems.get(id));
        return String.format(EngineConstants.WorkItemAssignedSuccessMessage, id, assignee);
    }

    private String unAssignCommand(int id, String assignee) {
        if (!workItems.containsKey(id))
            return String.format(EngineConstants.WorkItemDoesNotExistErrorMessage, id);
        if (!members.containsKey(assignee))
            return String.format(EngineConstants.MemberDoesNotExistErrorMessage, assignee);
        if (!members.get(assignee).getItems().containsKey(id))
            return String.format(EngineConstants.MemberDoesNotHaveWorkItemErrorMessage, assignee, id);

        members.get(assignee).removeWorkItem(id);
        if (workItems.get(id) instanceof Bug)
            ((Bug) workItems.get(id)).removeAssignee();

        return EngineConstants.WorkItemUnassignedSuccessMessage;
    }


    private String getMemberTeam(String name) {
        for (Team t : teams.values())
            for (Member m : t.getMembers().values())
                if (m.getName().equals(name))
                    return t.getName();
        return "";
    }

    private String getWorkItemTeam(int id) {
        for (Team t : teams.values())
            for (Board b : t.getBoards().values())
                for (WorkItem w : b.getItems().values())
                    if (w.getId() == id)
                        return t.getName();
        return "";
    }


    private String listWorkItemsByCategory(String category) {

        Stream<WorkItem> workItemStream = workItems.values().stream();

        switch (category.toLowerCase()) {
            case "bug":
                workItemStream = workItemStream.filter(workItem -> workItem instanceof Bug);
                break;
            case "story":
                workItemStream = workItemStream.filter(workItem -> workItem instanceof Story);
                break;
            case "feedback":
                workItemStream = workItemStream.filter(workItem -> workItem instanceof Feedback);
                break;
            case "all":
                break;
            default:
                return String.format(EngineConstants.InvalidCategoryErrorMessage, category);
        }

        String result = workItemStream.map(WorkItem::toString).collect(Collectors.joining("\n"));

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }

    private String sortWorkItems(String sortType) {
        Stream<WorkItem> workItemStream = workItems.values().stream();
        Stream<Feedback> feedbackStream = workItems.values().stream().filter(w -> w instanceof Feedback).map(w -> (Feedback) w);
        Stream<BugStory> bugStoryStream = workItems.values().stream().filter(w -> w instanceof BugStory).map(w -> (BugStory) w);
        Stream<Bug> bugStream = workItems.values().stream().filter(w -> w instanceof Bug).map(w -> (Bug) w);
        Stream<Story> storyStream = workItems.values().stream().filter(w -> w instanceof Story).map(w -> (Story) w);

        String result;

        switch (sortType.toLowerCase()) {
            case "title":
                result = workItemStream
                        .sorted(Comparator.comparing(WorkItem::getTitle))
                        .map(WorkItem::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "priority":
                result = bugStoryStream
                        .sorted(Comparator.comparing(BugStory::getPriority))
                        .map(WorkItem::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "severity":
                result = bugStream
                        .sorted(Comparator.comparing(Bug::getSeverity))
                        .map(Bug::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "size":
                result = storyStream
                        .sorted(Comparator.comparing(Story::getSize))
                        .map(Story::toString)
                        .collect(Collectors.joining("\n"));
                break;
            case "rating":
                result = feedbackStream
                        .sorted(Comparator.comparing(Feedback::getRating))
                        .map(WorkItem::toString)
                        .collect(Collectors.joining("\n"));
                break;
            default:
                return String.format(EngineConstants.InvalidSortTypeErrorMessage, sortType);
        }

        return result.length() == 0 ?
                EngineConstants.WorkItemListEmptyErrorMessage : result.trim();
    }


    /*private String listWorkItemsByStatus(String status) {
        switch (status.toLowerCase()) {

        }
    }*/
}
