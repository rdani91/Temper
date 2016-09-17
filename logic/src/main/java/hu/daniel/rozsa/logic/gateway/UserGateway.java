package hu.daniel.rozsa.logic.gateway;

import java.util.List;

import hu.daniel.rozsa.logic.OnCompleteResult;
import hu.daniel.rozsa.logic.entity.User;

public interface UserGateway {

    void getNearbyUsers(User.Gender gender, OnCompleteResult<List<User>> onCompleteResult);
}
