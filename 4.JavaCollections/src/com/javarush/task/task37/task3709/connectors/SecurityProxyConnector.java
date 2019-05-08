package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.*;

public class SecurityProxyConnector implements Connector {
    private SimpleConnector simpleConnector;
    private SecurityChecker securityChecker = new SecurityCheckerImpl();

    public SecurityProxyConnector(String connector) {
        this.simpleConnector = new SimpleConnector(connector);
    }

    @Override
    public void connect() {
        if (securityChecker.performSecurityCheck())
            simpleConnector.connect();
    }
}
