package DTO;

import entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateDto {
    private String firstName;
    private String secondName;
    private String email;
}
