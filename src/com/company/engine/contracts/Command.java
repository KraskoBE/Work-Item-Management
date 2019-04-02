package com.company.engine.contracts;

import java.util.List;

public interface Command {
    
    String getName();

    List<String> getParameters();
}
