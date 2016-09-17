package hu.daniel.rozsa.logic;

import hu.daniel.rozsa.logic.gateway.UserGateway;

public abstract class AbstractGatewayFactory implements GatewayFactory {

    private LazyInstance<UserGateway> userGatewayLazyInstance = new LazyInstance<UserGateway>() {
        @Override
        public UserGateway createInstance() {
            return createUserGateway();
        }
    };

    protected abstract UserGateway createUserGateway();

    @Override
    public UserGateway getUserGateway() {
        return userGatewayLazyInstance.getInstance();
    }
}
