package hu.daniel.rozsa.logic.interactor;

import hu.daniel.rozsa.logic.gateway.UserGateway;

public class UserInteractor {

private final UserGateway userGateway;

    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public boolean needToLogin() {
        return true;
    }
}
