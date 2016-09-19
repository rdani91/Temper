package hu.daniel.rozsa.logic.interactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;

public class NearbyUsersInteractor {

    private final NearbyUserGateway nearbyUserGateway;

    public NearbyUsersInteractor(NearbyUserGateway nearbyUserGateway) {
        this.nearbyUserGateway = nearbyUserGateway;
    }

    public void getNearbyUsers(User.Gender targetGender, OnCompleteResult<List<User>> onCompleteResult) {

        if (nearbyUserGateway.hasCachedData()) {
            List<User> cacheData = nearbyUserGateway.getCacheData();
            onCompleteResult.onSuccess(cacheData);
        } else {
            List<User> fakeUserList = new ArrayList<>();

            String[] fakeMaleUserNames = new String[]{
                    "Kalányos XBox360",
                    "Kim Jong Un",
                    "Remek Elek",
                    "Bármi Aron"
            };

            String[] fakeFemaleUserNames = new String[]{
                    "Mondom Teréz",
                    "Para Zita",
                    "Major Anna",
                    "Dia Dóra"
            };

            Random r = new Random();
            int userSize = r.nextInt(50) + 50;
            for (int i = 0; i < userSize; i++) {

                User generatedUser = new User();
                generatedUser.age = r.nextInt(40) + 18;
                generatedUser.gender = targetGender;
                generatedUser.location = "Szeged";
                generatedUser.name = targetGender.equals(User.Gender.MALE) ? fakeMaleUserNames[r.nextInt(4)] : fakeFemaleUserNames[r.nextInt(4)];
                fakeUserList.add(generatedUser);
            }
            nearbyUserGateway.saveUserData(fakeUserList);
            onCompleteResult.onSuccess(fakeUserList);
        }

    }
}
