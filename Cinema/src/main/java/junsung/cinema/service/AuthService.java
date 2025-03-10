package junsung.cinema.service;

import junsung.cinema.config.SecurityConfig;
import junsung.cinema.dto.UsersDTO;
import junsung.cinema.entity.Users;
import junsung.cinema.repository.UserRepository;
import junsung.cinema.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public Users login(String username, String password) {

        Users users = userRepository.findByUsername(username );

        if (users != null&& bcryptPasswordEncoder.matches(password,users.getPassword())) { // 실제 서비스에서는 DB 조회 필요

            return  users;
        }
        return null;}

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
