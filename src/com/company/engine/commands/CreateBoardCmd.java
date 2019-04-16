package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.contracts.Engine;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.unit.Board;

import java.util.List;

public final class CreateBoardCmd {

    public static String execute(EngineImpl engine, Factory factory, List<String> parameters) {
        if (parameters.size() != 2)
            return EngineConstants.InvalidNumberOfParameters;

        String boardName = parameters.get(0);
        String teamName = parameters.get(1);

        if (engine.getBoards().containsKey(boardName))
            return String.format(EngineConstants.BoardExistsInTeamErrorMessage, boardName, teamName);

        if (!engine.getTeams().containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);

        Board board = factory.createBoard(boardName);

        engine.getBoards().put(boardName, board);

        engine.getTeams().get(teamName).getBoards().put(boardName, board);
        engine.getTeams().get(teamName).addActivity(String.format("Board %s was added", boardName));

        return String.format(EngineConstants.BoardCreatedSuccessMessage, boardName);
    }

}
