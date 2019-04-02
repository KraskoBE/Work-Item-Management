package com.company.commands.factories;

import java.util.List;

public interface Command {

    String execute(List<String> parameters);
}
