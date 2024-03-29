package com.company.engine.commands;

import com.company.engine.EngineConstants;
import com.company.engine.EngineImpl;
import com.company.engine.contracts.Factory;
import com.company.models.contracts.unit.Member;

import java.util.List;

public final class CreatePersonCmd {

    public static String execute(EngineImpl engine, Factory factory, List<String> parameters) {
        if (parameters.size() != 1)
            return EngineConstants.InvalidNumberOfParameters;

        String memberName = parameters.get(0);

        if (engine.getMembers().containsKey(memberName))
            return String.format(EngineConstants.PersonExistsErrorMessage, memberName);

        Member member = factory.createMember(memberName);

        engine.getMembers().put(memberName, member);

        return String.format(EngineConstants.PersonCreatedSuccessMessage, memberName);
    }
}
