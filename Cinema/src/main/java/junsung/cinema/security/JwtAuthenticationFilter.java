package junsung.cinema.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String token = request.getHeader("Authorization");

       if (token != null && token.startsWith("Bearer ")) {
           token = token.substring(7);
           String username = jwtUtil.validateToken(token);//토큰 검증

           if (username != null) {
               UserDetails userDetails = new User(username,"", Collections.emptyList());
               SecurityContextHolder.getContext().setAuthentication(
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
               );
           }
       }
       filterChain.doFilter(request,response);
    }
}
