package hu.daniel.rozsa.logic;

import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public interface GatewayFactory {

    NearbyUserGateway getNearbyUsersGateway();

    UserGateway getUserGateway();
}
