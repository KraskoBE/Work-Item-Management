package com.company;

import com.company.core.EngineImpl;
import com.company.core.contracts.Engine;
import com.company.core.factories.Factory;
import com.company.core.factories.FactoryImpl;

public class Main {

    public static void main(String[] args) {
        Factory factory = new FactoryImpl();
        Engine engine = new EngineImpl(factory);

        engine.start();
    }
}
