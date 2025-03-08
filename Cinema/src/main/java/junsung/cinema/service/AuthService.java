package junsung.cinema.service;

import junsung.cinema.config.SecurityConfig;
import junsung.cinema.entity.Users;
import junsung.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public Users signUp(String username, String password1, String password2 , String email , String phone, String name, String birth) {
        if (userRepository.findByEmail(email) != null)
        {throw new IllegalArgumentException("이미 존재하는 email입니다");}
        if(!password1.equals(password2)) {            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(bcryptPasswordEncoder.encode(password1));
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setBirth(birth);
        user.setCount(0);

        userRepository.save(user);
        return user;
    }
}
/*
private String username;
private String password;
private String email;
private String phone;
private String birth;
private String name;

private int count;*/