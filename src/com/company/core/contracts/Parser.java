package com.company.core.contracts;

import com.company.commands.factories.Command;

import java.util.List;

public interface Parser {

    Command parseCommand(String fullCommand);

    List<String> parseParameters(String fullCommand);
}
