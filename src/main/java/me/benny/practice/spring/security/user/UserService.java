package me.benny.practice.spring.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     *  유저등록
     * @Param username username
     * @Param password password
     * @Return 유저 권한을 가지고 있는 유저
     */
    public User signup(
            String username,
            String password
    ){
        if(userRepository.findByUsername(username) != null) {
            throw new AlreadyRegisteredUserExcepiton();
        }
        return userRepository.save(new User(username
                                            ,passwordEncoder.encode(password),
                                            "ROLE_USER")
                                    );
    }

    /**
     * 관리자 등록
     * @Param username username
     * @Param password password
     * @Return 관리자 권한을 가지고 있는 유저
     */
    public User signupAdmin(
        String username,
        String password
    ){
        if(userRepository.findByUsername(username) != null){
            throw new AlreadyRegisteredUserExcepiton();
        }
        return userRepository.save(new User(username, passwordEncoder.encode(password),"ROLE_ADMIN"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
