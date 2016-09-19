package hu.daniel.rozsa;

import hu.daniel.rozsa.gateway.FakeNearbyUserGateway;
import hu.daniel.rozsa.gateway.FakeUserGateway;
import hu.daniel.rozsa.logic.AbstractGatewayFactory;
import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public class DefaultGatewayFactory extends AbstractGatewayFactory {
    @Override
    protected UserGateway createUserGateway() {
        return new FakeUserGateway();
    }

    @Override
    protected NearbyUserGateway createNearbyUsersGateway() {
        return new FakeNearbyUserGateway();
    }
}
