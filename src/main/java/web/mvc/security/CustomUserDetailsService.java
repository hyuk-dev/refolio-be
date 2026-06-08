package web.mvc.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.mvc.domain.User;
import web.mvc.repository.UserRepository;

@Service //생성
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       log.info("username : {}" , username); //id
        //db에서 select..
       User findUser =  userRepository.findByUsername(username);
       if(findUser!=null){
          log.info("찾았다....");
          return new CustomUserDetails(findUser);
       }
        log.info("못찾았다....");
       throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
    }

}
