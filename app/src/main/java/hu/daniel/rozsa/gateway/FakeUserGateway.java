package hu.daniel.rozsa.gateway;

import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public class FakeUserGateway implements UserGateway {
    private User loggedInUser;

    @Override
    public boolean needToLogin() {
        return loggedInUser == null;
    }



}
