package DTO;

import entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserViewDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String userState;
    private int userStateId;

    public UserViewDto(User user, String userState) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.email = user.getEmail();
        this.userState = userState;
        this.userStateId = user.getUserState();
    }
}
