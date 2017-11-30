package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserLoggedDto implements Serializable {
    private String message;
    private String redirect;
    private boolean authorized;
    private Long userId;
    private String firstName;
    private String secondName;
    private int userStateId;


    public UserLoggedDto(String message, boolean authorized) {
        this.message = message;
        this.authorized = authorized;
    }

    public UserLoggedDto(String message, String redirect) {
        this.message = message;
        this.redirect = redirect;
    }

    public UserLoggedDto(String message) {
        this.message = message;

    }
}
