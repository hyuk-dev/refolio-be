package web.mvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import web.mvc.domain.User;

@Setter
@Getter
@ToString
public class SignupRequestDto {
    private String username;
    private String password;

    public User toUser(SignupRequestDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role("ROLE_USER")
                .build();
    }
}
