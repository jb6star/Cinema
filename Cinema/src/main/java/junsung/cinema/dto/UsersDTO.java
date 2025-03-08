package junsung.cinema.dto;

import junsung.cinema.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UsersDTO {

    private String username;
    private String email;
    private String name;

    public UsersDTO(Users users) {
        this.username = users.getUsername();
        this.email = users.getEmail();
        this.name = users.getName();
    }
}
