package junsung.cinema.controller;


import junsung.cinema.dto.UsersDTO;
import junsung.cinema.dto.LoginRequest;
import junsung.cinema.entity.Users;
import junsung.cinema.security.JwtUtil;
import junsung.cinema.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")

public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;





    @PostMapping("/login")
    public ResponseEntity<UsersDTO> login(@RequestBody LoginRequest loginRequest ) {
        Users users = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        if (token == null){
            throw new IllegalArgumentException("사용자 정보를 찾을 수 없습니다.");
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(new UsersDTO(users));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsersDTO> signup(@RequestBody Users user) {
        Users users = authService.signUp(user.getUsername(), user.getPassword(), user.getPass(),
                user.getEmail(), user.getPhone(), user.getName(), user.getBirth());

        return ResponseEntity.ok(new UsersDTO(users));
    }



}
