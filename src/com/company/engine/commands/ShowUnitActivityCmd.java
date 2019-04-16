package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.models.contracts.unit.Unit;
import com.company.models.unit.MemberImpl;

import java.util.List;

public final class ShowUnitActivityCmd {
    public static String execute(EngineImpl engine, List<String> parameters, String type) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;

        String unitName = parameters.get(0);
        Unit unit = new MemberImpl("tempUnit");

        if (type.equals("board")) {
            if (!engine.getBoards().containsKey(unitName))
                return String.format(EngineConstants.BoardDoesNotExistErrorMessage, unitName);

            unit = engine.getBoards().get(unitName);
        }

        if (type.equals("person")) {
            if (!engine.getMembers().containsKey(unitName))
                return String.format(EngineConstants.PersonDoesNotExistErrorMessage, unitName);

            unit = engine.getMembers().get(unitName);
        }

        return String.format("[%s's activity]\n", unitName) +
                String.join("\n", unit
                        .getActivityHistory())
                        .trim();
    }
}
