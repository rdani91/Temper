package hu.daniel.rozsa.gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public class FakeUserGateway implements UserGateway {
    @Override
    public void getNearbyUsers(User.Gender gender, OnCompleteResult<List<User>> onCompleteResult) {
        List<User> fakeUserList = new ArrayList<>();
        String[] fakeMaleUserNames = new String[]{
                "Kalányos XBox360",
                "Kim Jong Un",
                "Remek Elek",
                "Bármi Áron"
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
            generatedUser.gender = gender;
            generatedUser.location = "Szeged";
            generatedUser.name = gender.equals(User.Gender.MALE) ? fakeMaleUserNames[r.nextInt(4)] : fakeFemaleUserNames[r.nextInt(4)];
            fakeUserList.add(generatedUser);
        }

        onCompleteResult.onSuccess(fakeUserList);
    }
}
