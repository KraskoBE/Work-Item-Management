package com.company;


import com.company.engine.EngineImpl;
import com.company.engine.contracts.Engine;

public class Main {

    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.start();
    }
}
