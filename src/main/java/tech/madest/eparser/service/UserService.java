package tech.madest.eparser.service;

import tech.madest.eparser.model.User;

public interface UserService {

    User findOne( String username);
}
