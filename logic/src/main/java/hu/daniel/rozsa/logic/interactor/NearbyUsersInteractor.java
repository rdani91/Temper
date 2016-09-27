package hu.daniel.rozsa.logic.interactor;

import java.util.List;

import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;

public class NearbyUsersInteractor {

    private final NearbyUserGateway nearbyUserGateway;

    public NearbyUsersInteractor(NearbyUserGateway nearbyUserGateway) {
        this.nearbyUserGateway = nearbyUserGateway;
    }

    public void getNearbyUsers(final OnCompleteResult<List<User>> onCompleteResult) {

        if (nearbyUserGateway.hasCachedData()) {
            List<User> cacheData = nearbyUserGateway.getCacheData();
            onCompleteResult.onSuccess(cacheData);
        } else {
            nearbyUserGateway.getNearbyUsers(new OnCompleteResult<List<User>>() {
                @Override
                public void onSuccess(List<User> result) {
                    nearbyUserGateway.saveUserData(result);
                    onCompleteResult.onSuccess(result);
                }

                @Override
                public void onError(String errorMsg) {
                    onCompleteResult.onError(errorMsg);
                }
            });
        }

    }
}
