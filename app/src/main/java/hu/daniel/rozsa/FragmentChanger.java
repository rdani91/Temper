package hu.daniel.rozsa;

import hu.daniel.rozsa.logic.entity.User;

public interface FragmentChanger {

    void changeFragment();

    void onLoginSuccess(User user);
}
