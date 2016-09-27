package hu.daniel.rozsa.gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.NearbyUserGateway;

public class FakeNearbyUserGateway implements NearbyUserGateway {
    private List<User> inMemoryCache;
    private String[] fakeMaleFirstNames = new String[]{
            "Kaine",
            "Rodrigez",
            "Emilio",
            "Laszlo",
            "Istvan",
            "Jon",
            "Ragnar",
            "Lagertha"
    };
    private String[] fakeMaleLastNames = new String[]{
            "Kim Jong",
            "Lakatos",
            "Szurke",
            "Snow",
            "Lothbrok"
    };


    @Override
    public boolean hasCachedData() {
        return inMemoryCache != null;
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

    @Override
    public void getNearbyUsers(OnCompleteResult<List<User>> onCompleteResult) {
        List<User> fakeUserList = new ArrayList<>();

        Random r = new Random();
        int userSize = r.nextInt(50) + 50;
        for (int i = 0; i < userSize; i++) {

            User generatedUser = new User();
            generatedUser.age = r.nextInt(40) + 18;
            generatedUser.location = "Szeged";
            generatedUser.profileResId = r.nextInt(7);
            generatedUser.name = fakeMaleFirstNames[r.nextInt(fakeMaleFirstNames.length)] +
                    " " +
                    fakeMaleLastNames[r.nextInt(fakeMaleLastNames.length)];
            fakeUserList.add(generatedUser);
        }

        onCompleteResult.onSuccess(fakeUserList);
    }


}
