package com.blog.auth;

import com.blog.domain.User;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Bean등록
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌때 username, password 변수 2개를 가로채는데 password부분은 알아서 합니다.
    //username이 DB에 있는지 확인을 해줘야 합니다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("username 넘어온 것이다 = {}",username);
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" + username);
        });

        return new PrincipalDetail(user); //시큐리티의 세션에 유저정보가 저장이 됩니다.
    }
}
