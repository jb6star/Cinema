package junsung.cinema.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "5MvL9vLJocKv5dXf99AsvYj5wPzO4Rx7QfVd+RmS9oQ="; // Base64 인코딩된 256비트 키
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private Key getSigningKey() {
     return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());}

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
//============================로그인 후 필요한 메서드 =====================
    public String validateToken(String token){ //얜 JWT 객체가 존재하는지의 검증
        try{
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); //username 반환
        }catch(Exception e){
            return null;
        }
    }
}

