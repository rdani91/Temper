package hu.daniel.rozsa.gateway;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;

public class FakeNearbyUserGateway implements NearbyUserGateway {
    private List<User> inMemoryCache;

    @Override
    public boolean hasCachedData() {
        return inMemoryCache!=null;
    }

    @Override
    public List<User> getCacheData() {
        return inMemoryCache;
    }

    @Override
    public void saveUserData(List<User> nearbyUsersToSave) {
        inMemoryCache = new ArrayList<>();
        inMemoryCache.addAll(nearbyUsersToSave);
    }


}
