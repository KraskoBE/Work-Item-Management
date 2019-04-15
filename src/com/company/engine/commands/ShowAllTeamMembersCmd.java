package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;

import java.util.List;

public final class ShowAllTeamMembersCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;

        String teamName = parameters.get(0);

        if (!engine.getTeams().containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);

        if (engine.getTeams().get(teamName).getMembers().isEmpty())
            return EngineConstants.NoMembersErrorMessage;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[MEMBERS of %s]\n", teamName));
        engine.getTeams().get(teamName).getMembers().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }
}
