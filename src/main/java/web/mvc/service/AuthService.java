package web.mvc.service;

import web.mvc.domain.User;

public interface UserService {
    void register(User user);

    void login(User user);

    boolean isExist(String username);
}
