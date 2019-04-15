package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;

import java.util.List;

public final class ShowAllTeamBoardsCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;

        String teamName = parameters.get(0);

        if (!engine.getTeams().containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);

        if (engine.getTeams().get(teamName).getBoards().isEmpty())
            return String.format(EngineConstants.EmptyBoardsErrorMessage, teamName);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[BOARDS of %s]\n", teamName));
        engine.getTeams().get(teamName).getBoards().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }
}
