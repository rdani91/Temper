package hu.daniel.rozsa.logic;

import hu.daniel.rozsa.logic.interactor.NearbyUsersInteractor;
import hu.daniel.rozsa.logic.interactor.UserInteractor;

public abstract class AbstractLogic {

    private LazyInstance<GatewayFactory> gatewayFactoryLazyInstance = new LazyInstance<GatewayFactory>() {
        @Override
        protected GatewayFactory createInstance() {
            return createGatewayFactory();
        }
    };

    private LazyInstance<PluginFactory> pluginFactoryLazyInstance = new LazyInstance<PluginFactory>() {
        @Override
        protected PluginFactory createInstance() {
            return createPluginFactory();
        }
    };

    private LazyInstance<NearbyUsersInteractor> nearbyUsersInteractorLazyInstance = new LazyInstance<NearbyUsersInteractor>() {
        @Override
        protected NearbyUsersInteractor createInstance() {
            return createNearbyUserInteractor();
        }
    };

    private LazyInstance<UserInteractor> userInteractorLazyInstance = new LazyInstance<UserInteractor>() {
        @Override
        protected UserInteractor createInstance() {
            return createUserInteractor();
        }
    };

    private NearbyUsersInteractor createNearbyUserInteractor() {
        return new NearbyUsersInteractor(getGatewayFactory().getNearbyUsersGateway());
    }

    protected abstract GatewayFactory createGatewayFactory();

    protected abstract PluginFactory createPluginFactory();

    private GatewayFactory getGatewayFactory() {
        return gatewayFactoryLazyInstance.getInstance();
    }

    private PluginFactory getPluginFactory() {
        return pluginFactoryLazyInstance.getInstance();
    }

    public NearbyUsersInteractor getNearbyUsersInteractor() {
        return nearbyUsersInteractorLazyInstance.getInstance();
    }

    public UserInteractor getUserInteractor(){
        return userInteractorLazyInstance.getInstance();
    }

    private UserInteractor createUserInteractor() {
        return new UserInteractor(getGatewayFactory().getUserGateway());
    }
}
