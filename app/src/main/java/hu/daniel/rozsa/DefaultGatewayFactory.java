package hu.daniel.rozsa;

import hu.daniel.rozsa.gateway.FakeUserGateway;
import hu.daniel.rozsa.logic.AbstractGatewayFactory;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public class DefaultGatewayFactory extends AbstractGatewayFactory {
    @Override
    protected UserGateway createUserGateway() {
        return new FakeUserGateway();
    }
}
