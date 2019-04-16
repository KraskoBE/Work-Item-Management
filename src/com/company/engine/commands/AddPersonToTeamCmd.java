package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;

import java.util.List;

public final class AddPersonToTeamCmd {

    public static String execute(EngineImpl engine, List<String> parameters) {
        if (parameters.size() != 2)
            return EngineConstants.InvalidNumberOfParameters;

        String memberName = parameters.get(0);
        String teamName = parameters.get(1);

        if (!engine.getTeams().containsKey(teamName))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, teamName);

        if (!engine.getMembers().containsKey(memberName))
            return String.format(EngineConstants.PersonDoesNotExistErrorMessage, memberName);

        if (engine.getTeams().get(teamName).getMembers().containsKey(memberName))
            return String.format(EngineConstants.PersonAlreadyInTeamErrorMessage, memberName, teamName);

        engine.getTeams().get(teamName).getMembers().put(memberName, engine.getMembers().get(memberName));
        engine.getMembers().get(memberName).addActivity(String.format(EngineConstants.PersonJoined_PersonActivity, teamName));
        engine.getTeams().get(teamName).addActivity(String.format(EngineConstants.PersonJoined_TeamActivity, memberName));
        return String.format(EngineConstants.PersonAddedSuccessMessage, memberName, teamName);
    }
}
