package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.Team;

import java.util.List;

public final class CreateTeamCmd {

    public static String execute(EngineImpl engine, Factory factory, List<String> parameters) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;

        String teamName = parameters.get(0);

        if (engine.getTeams().containsKey(teamName))
            return String.format(EngineConstants.TeamExistsErrorMessage, teamName);

        Team team = factory.createTeam(teamName);

        engine.getTeams().put(teamName, team);

        return String.format(EngineConstants.TeamCreatedSuccessMessage, teamName);

    }
}
