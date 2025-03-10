package junsung.cinema.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
public class Users implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String pass;
    private String email;
    private String phone;
    private String birth;
    private String name;

    private int count;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
