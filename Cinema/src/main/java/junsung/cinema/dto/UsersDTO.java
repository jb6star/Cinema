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
    private String phone;
    private String password;

    public UsersDTO(Users users,String username, String password){
        this.username = users.getUsername();
        this.password = users.getPassword();
    }

    public UsersDTO(Users users) {
        this.username = users.getUsername();
        this.email = users.getEmail();
        this.name = users.getName();
        this.phone = users.getPhone();
    }
}
