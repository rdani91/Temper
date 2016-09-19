package hu.daniel.rozsa.logic;

import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public abstract class AbstractGatewayFactory implements GatewayFactory {

    private LazyInstance<NearbyUserGateway> nearbyUserGatewayLazyInstance = new LazyInstance<NearbyUserGateway>() {
        @Override
        public NearbyUserGateway createInstance() {
            return createNearbyUsersGateway();
        }
    };

    private LazyInstance<UserGateway> userGatewayLazyInstance = new LazyInstance<UserGateway>() {
        @Override
        protected UserGateway createInstance() {
            return createUserGateway();
        }
    };

    protected abstract UserGateway createUserGateway();

    protected abstract NearbyUserGateway createNearbyUsersGateway();

    @Override
    public NearbyUserGateway getNearbyUsersGateway() {
        return nearbyUserGatewayLazyInstance.getInstance();
    }

    @Override
    public UserGateway getUserGateway() {
        return userGatewayLazyInstance.getInstance();
    }

}
