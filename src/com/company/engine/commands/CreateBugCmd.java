package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.workItem.Bug;


import java.util.List;

public final class CreateBugCmd {

    public static String execute(EngineImpl engine, Factory factory, List<String> parameters) {
        if (parameters.size() != 6)
            return EngineConstants.InvalidNumberOfParameters;

        String bugName = parameters.get(0);
        String bugDescription = parameters.get(1);
        String bugPriority = parameters.get(2);
        String bugSeverity = parameters.get(3);
        String bugBoard = parameters.get(4);
        String bugTeam = parameters.get(5);

        if (!engine.getTeams().containsKey(bugTeam))
            return String.format(EngineConstants.TeamDoesNotExistErrorMessage, bugTeam);

        if (!engine.getTeams().get(bugTeam).getBoards().containsKey(bugBoard))
            return String.format(EngineConstants.BoardIsNotOnTheTeamErrorMessage, bugBoard, bugTeam);

        Bug bug = factory.createBug(engine.getGlobalID(), bugName, bugDescription, bugPriority, bugSeverity);

        engine.getWorkItems().put(engine.getGlobalID(), bug);
        engine.getTeams().get(bugTeam).getBoards().get(bugBoard).addWorkItem(bug);

        return String.format(EngineConstants.BugCreatedSuccessMessage, bugName, engine.getGlobalIDWithIncrease());
    }
}