package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;

import java.util.List;
import java.util.stream.Collectors;

public final class ShowTeamActivityCmd {
    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;

        String teamName = parameters.get(0);

        if (!engine.getTeams().containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);

        String result = String.format("[%s activity]\n", teamName) +
                engine.getTeams()
                        .values()
                        .stream()
                        .filter(team -> team.getName().equals(teamName))
                        .flatMap(t -> t.getActivity().stream())
                        .collect(Collectors.joining("\n"));
        return result.trim();
    }
}
