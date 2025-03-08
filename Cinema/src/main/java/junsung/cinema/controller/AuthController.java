package junsung.cinema.controller;


import junsung.cinema.dto.UsersDTO;
import junsung.cinema.entity.Users;
import junsung.cinema.repository.UserRepository;
import junsung.cinema.security.JwtUtil;
import junsung.cinema.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

        if (userRepository.findByUsernameAndPassword(username,password) != null) { // 실제 서비스에서는 DB 조회 필요
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/signup")
    public ResponseEntity<UsersDTO> signup(@RequestParam  String username
                                    , @RequestParam String password1
                                    , @RequestParam String password2
                                    , @RequestParam String email
                                    , @RequestParam String phone
                                    , @RequestParam String name
                                    , @RequestParam String birth) {
        Users users = authService.signUp(username, password1, password2, email, phone, name, birth);

        return ResponseEntity.ok(new UsersDTO(users));
    }



}
