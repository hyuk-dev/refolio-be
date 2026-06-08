package web.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.User;
import web.mvc.dto.SignupRequestDto;
import web.mvc.exception.CommonException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(SignupRequestDto dto) {
        User user = dto.toUser(dto);
        // 회원 등록 로직 구현
        if(isExist(user.getUsername())){
            throw new CommonException(ErrorCode.DUPLICATED_USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(String username) {
        if(userRepository.findByUsername(username)!=null){
            return true;
        }
        return false;
    }
}
