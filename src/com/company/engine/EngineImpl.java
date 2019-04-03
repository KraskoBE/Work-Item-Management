package com.company.engine;

import com.company.engine.contracts.Command;
import com.company.engine.contracts.Engine;
import com.company.engine.contracts.Factory;
import com.company.engine.contracts.Renderer;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.Bug;
import com.company.models.contracts.workItem.Story;
import com.company.models.contracts.workItem.WorkItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineImpl implements Engine {

    private Factory factory;

    private int globalID = 0;

    private Map<String, Team> teams;
    private Map<String, Member> members;
    private Map<String, Board> boards;
    private Map<Integer, WorkItem> workItems;

    private Renderer renderer;

    public EngineImpl() {

        factory = new FactoryImpl();
        teams = new HashMap<>();
        members = new HashMap<>();
        boards = new HashMap<>();
        workItems = new HashMap<>();
        renderer = new ConsoleRendererImpl();
    }


    @Override
    public void start() {
        List<String> commandResults = new ArrayList<>();
        try {
            List<Command> commands = readCommands();
            commandResults = processCommands(commands);
        } catch (Exception ex) {
            commandResults.add(ex.getMessage());
        }

        renderCommandResults(commandResults);
    }

    private List<Command> readCommands() {

        List<Command> commands = new ArrayList<>();

        for (String currentLine : renderer.input()) {
            Command currentCommand = CommandImpl.parse(currentLine);
            commands.add(currentCommand);
        }
        return commands;
    }

    private List<String> processCommands(List<Command> commands) {
        List<String> commandResults = new ArrayList<>();

        for (Command command : commands) {
            String commandResult;

            switch (command.getName()) {
                case EngineConstants.CreateMemberCommand:
                    String memberName = command.getParameters().get(0);

                    commandResult = createMember(memberName);
                    commandResults.add(commandResult);
                    break;
                case EngineConstants.CreateTeamCommand:
                    String teamName = command.getParameters().get(0);

                    commandResult = createTeam(teamName);
                    commandResults.add(commandResult);
                    break;
                case EngineConstants.CreateBoardCommand:
                    String boardName = command.getParameters().get(0);
                    teamName = command.getParameters().get(1);

                    commandResult = createBoard(boardName, teamName);
                    commandResults.add(commandResult);
                    break;
                case EngineConstants.CreateBugCommand:
                    String bugName = command.getParameters().get(0);
                    String bugDescription = command.getParameters().get(1);
                    String bugPriority = command.getParameters().get(2);
                    String bugSeverity = command.getParameters().get(3);
                    String bugStatus = command.getParameters().get(4);
                    String bugBoard = command.getParameters().get(5);
                    String bugTeam = command.getParameters().get(6);

                    commandResult = createBug(bugName, bugDescription, bugPriority, bugSeverity, bugStatus, bugBoard, bugTeam);
                    commandResults.add(commandResult);
                    break;
                case EngineConstants.CraeteStoryCommand:
                    String storyName = command.getParameters().get(0);
                    String storyDescription = command.getParameters().get(1);
                    String storyStatus = command.getParameters().get(2);
                    String storyPriority = command.getParameters().get(3);
                    String storySize = command.getParameters().get(4);
                    String storyBoard = command.getParameters().get(5);
                    String storyTeam = command.getParameters().get(6);

                    commandResult = createStory(storyName, storyDescription, storyStatus, storyPriority, storySize, storyBoard, storyTeam);
                    commandResults.add(commandResult);
                    break;
                case EngineConstants.AddMemberToTeam:
                    memberName = command.getParameters().get(0);
                    teamName = command.getParameters().get(1);

                    commandResult = addMemberToTeam(memberName, teamName);
                    commandResults.add(commandResult);
                    break;
                default:
                    commandResults.add(String.format(EngineConstants.InvalidCommandErrorMessage, command.getName()));
                    break;
            }
        }

        return commandResults;
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

    private String createBug(String name, String description, String priority, String severity, String status, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnheTeam, board, team);

        Bug bug = factory.createBug(globalID, name, description, priority, severity, status);
        workItems.put(globalID, bug);

        teams.get(team).getBoards().get(board).addWorkItem(bug);
        return String.format(EngineConstants.BugCreatedSuccessMessage, name, globalID++);
    }

    private String createStory(String name, String description, String status, String priority, String size, String board, String team) {
        if (!teams.containsKey(team))
            return String.format(EngineConstants.TeamDoesNotExist, team);

        if (!teams.get(team).getBoards().containsKey(board))
            return String.format(EngineConstants.BoardIsNotOnheTeam, board, team);

        Story story = factory.createStory(globalID, name, description, status, priority, size);
        workItems.put(globalID,story);

        teams.get(team).getBoards().get(board).addWorkItem(story);
        return String.format(EngineConstants.StoryCreatedSuccessMessage, name, globalID++);
    }

    private String addMemberToTeam(String memberName, String teamName) {
        if (!teams.containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExist, teamName);

        if (!members.containsKey(memberName))
            return String.format(EngineConstants.MemberDoesNotExist, memberName);

        if (teams.get(teamName).getMembers().containsKey(memberName))
            return String.format(EngineConstants.MemberAlreadyInTeam, memberName, teamName);

        teams.get(teamName).getMembers().put(memberName, members.get(memberName));

        return String.format(EngineConstants.MemberAddedSuccessMessage, memberName, teamName);
    }


    private void renderCommandResults(List<String> output) {
        renderer.output(output);
    }

}
