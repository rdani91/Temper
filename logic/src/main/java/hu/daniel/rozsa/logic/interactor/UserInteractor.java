package hu.daniel.rozsa.logic.interactor;

import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;
import hu.daniel.rozsa.logic.gateway.UserGateway;

public class UserInteractor {

    private final UserGateway userGateway;

    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public boolean needToLogin() {
        return userGateway.needToLogin();
    }

    public void login(String userName, String password, OnCompleteResult<User> onCompleteResult) {

        if (userName.isEmpty() || password.isEmpty()) {
            onCompleteResult.onError("Please fill both fields");
            return;
        }

        User fakeUser = new User();
        fakeUser.name = userName;
        fakeUser.e_mail = "test@test.com";
        fakeUser.age = 25;
        fakeUser.gender = User.Gender.MALE;
        fakeUser.location = "Szeged";

        onCompleteResult.onSuccess(fakeUser);
    }
}
