package web.mvc.service;

import web.mvc.dto.auth.SignupRequest;

public interface AuthService {
    void register(SignupRequest dto);

    boolean isExist(String username);
}
