package com.company.engine;

import com.company.engine.contracts.Command;
import com.company.engine.contracts.Engine;
import com.company.engine.contracts.Factory;
import com.company.engine.contracts.Renderer;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.WorkItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineImpl implements Engine {
    private Factory factory;

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
                    commandResult = createBoard(boardName);
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

    private String createBoard(String name) {
        if (boards.containsKey(name)) {
            return String.format(EngineConstants.BoardExistsErrorMessage, name);
        }
        Board board = factory.createBoard(name);
        boards.put(name, board);

        return String.format(EngineConstants.BoardCreatedSuccessMessage, name);
    }


    private void renderCommandResults(List<String> output) {
        renderer.output(output);
    }

}
