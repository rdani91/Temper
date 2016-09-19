package hu.daniel.rozsa.logic.gateway;

import java.util.List;

import hu.daniel.rozsa.logic.entity.User;

public interface NearbyUserGateway {

    boolean hasCachedData();

    List<User> getCacheData();

    void saveUserData(List<User> nearbyUsersToSave);
}
