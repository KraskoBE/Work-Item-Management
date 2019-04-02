package com.company.core;

import com.company.commands.factories.Command;
import com.company.core.contracts.Engine;
import com.company.core.contracts.Parser;
import com.company.core.contracts.Reader;
import com.company.core.contracts.Writer;
import com.company.core.factories.Factory;
import com.company.core.providers.CommandParser;
import com.company.core.providers.ConsoleReader;
import com.company.core.providers.ConsoleWriter;
import com.company.models.contracts.Team;
import com.company.models.contracts.unit.Board;
import com.company.models.contracts.unit.Member;
import com.company.models.contracts.workItem.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class EngineImpl implements Engine {
    private static final String TERMINATION_COMMAND = "Exit";

    private Reader reader;
    private Writer writer;
    private Parser parser;

    private final List<Team> teams;
    private final List<Member> members;
    private final List<Board> boards;
    private final List<WorkItem> workItems;

    public EngineImpl(Factory factory) {
        reader = new ConsoleReader();
        writer = new ConsoleWriter();
        parser = new CommandParser(factory, this);

        teams= new ArrayList<>();
        members = new ArrayList<>();
        boards = new ArrayList<>();
        workItems = new ArrayList<>();
    }

    @Override
    public void start() {
        while (true) {
            try {
                String commandAsString = reader.readLine();
                if (commandAsString.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(commandAsString);
            } catch (Exception ex) {
                writer.writeLine(ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : ex.toString());
                //writer.writeLine("####################");
            }
        }
    }

    private void processCommand(String commandAsString) {
        if (commandAsString == null || commandAsString.trim().equals("")) {
            throw new IllegalArgumentException("Command cannot be null or empty.");
        }
        Command command = parser.parseCommand(commandAsString);
        List<String> parameters = parser.parseParameters(commandAsString);
        String executionResult = command.execute(parameters);
        writer.writeLine(executionResult);
        //writer.writeLine("####################");
    }
}
