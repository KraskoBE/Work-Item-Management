package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;

public final class ShowAllTeamsCmd {

    public static String showAllTeams(EngineImpl engine) {

        if (engine.getTeams().isEmpty())
            return EngineConstants.NoTeamsErrorMessage;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[TEAMS]\n");
        engine.getTeams().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }
}
