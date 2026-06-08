package web.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.User;
import web.mvc.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(User user) {
        // 회원 등록 로직 구현
        System.out.println("회원 등록: " + user.getUsername());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void login(User user) {
        // 로그인 로직 구현
        System.out.println("로그인 시도: " + user.getUsername());
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
