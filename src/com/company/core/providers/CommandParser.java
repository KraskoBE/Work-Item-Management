package com.company.core.providers;

import com.company.commands.factories.Command;
import com.company.core.contracts.Engine;
import com.company.core.contracts.Parser;
import com.company.core.factories.Factory;

import java.util.ArrayList;
import java.util.List;

public class CommandParser implements Parser {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    private final Factory factory;
    private final Engine engine;

    public CommandParser(Factory factory, Engine engine) {
        this.factory = factory;
        this.engine = engine;
    }

    public Command parseCommand(String fullCommand) {
        String commandName = fullCommand.split(" ")[0];
        return findCommand(commandName);
    }

    public List<String> parseParameters(String fullCommand) {
        String[] commandParts = fullCommand.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    private Command findCommand(String commandName) {
        switch (commandName.toLowerCase()) {
            /*case "createairplane":
                return new CreateAirplaneCommand(factory, engine);

            case "createbus":
                return new CreateBusCommand(factory, engine);

            case "createjourney":
                return new CreateJourneyCommand(factory, engine);

            case "createticket":
                return new CreateTicketCommand(factory, engine);

            case "createtrain":
                return new CreateTrainCommand(factory, engine);

            case "listjourneys":
                return new ListJourneysCommand(factory, engine);

            case "listtickets":
                return new ListTicketsCommand(factory, engine);

            case "listvehicles":
                return new ListVehiclesCommand(factory, engine);*/
        }
        throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
    }
}