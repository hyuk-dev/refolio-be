package web.mvc.service;

import web.mvc.domain.User;
import web.mvc.dto.SignupRequestDto;

public interface AuthService {
    void register(SignupRequestDto dto);

    boolean isExist(String username);
}
