package web.mvc.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import web.mvc.domain.User;

@Setter
@Getter
@ToString
public class SignupRequest {
    private String username;
    private String password;

    public User toUser(SignupRequest dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role("ROLE_USER")
                .build();
    }
}
