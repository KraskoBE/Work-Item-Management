package com.company.core.providers;

import com.company.core.contracts.Writer;

public class ConsoleWriter implements Writer {

    public void write(String message) {
        System.out.print(message);
    }

    public void writeLine(String message) {
        System.out.println(message);
    }
}
