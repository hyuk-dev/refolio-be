package web.mvc.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import web.mvc.domain.User;

@Setter
@Getter
@ToString(exclude = "password")
public class SignupRequest {
    private String username;
    private String password;
    private String nickname;

    public User toUser(SignupRequest dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .role("ROLE_USER")
                .build();
    }
}
