package hu.daniel.rozsa.logic;

import hu.daniel.rozsa.logic.gateway.UserGateway;

public interface GatewayFactory {

    UserGateway getUserGateway();
}
