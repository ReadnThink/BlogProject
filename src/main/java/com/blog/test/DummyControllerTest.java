package com.blog.test;

import com.blog.domain.User;
import com.blog.domain.UserRole;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository; // 스프링 빈에 등록이 된다(메모리에 등록이 된다.).

    @PostMapping("/dummy/join")
    public String join(@RequestBody User user) {
        user.setRole(UserRole.ROLE_USER);
        System.out.println(user);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User requestUser) {
        requestUser.setId(id);
        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("수정에 실패했습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        return null;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 없습니다.";
        }
        return "삭제되었습니다. id:" + id;
    }


    /**
     * 조회
     */
    @GetMapping("/dummy/user")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        List<User> lists = list.getContent();
        return lists;
    }
}
