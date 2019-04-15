package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;

public final class ShowAllPeopleCmd {

    public static String execute(EngineImpl engine) {
        if (engine.getMembers().isEmpty())
            return EngineConstants.NoMembersErrorMessage;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[PEOPLE]\n");
        engine.getMembers().forEach((k, v) -> stringBuilder.append(v.getName()).append('\n'));

        return stringBuilder.toString().trim();
    }
}
