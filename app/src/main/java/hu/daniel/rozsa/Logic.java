package hu.daniel.rozsa;

import hu.daniel.rozsa.logic.AbstractLogic;
import hu.daniel.rozsa.logic.GatewayFactory;

public class Logic extends AbstractLogic {
    private static Logic INSTANCE = new Logic();

    private Logic() {
    }
    public static Logic getInstance(){
        return INSTANCE;
    }

    @Override
    protected GatewayFactory createGatewayFactory() {
        return new DefaultGatewayFactory();
    }
}
