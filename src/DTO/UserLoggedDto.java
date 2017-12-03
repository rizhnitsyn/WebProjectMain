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
    private String redirectPath;
    private boolean authorized;
    private boolean error;
    private Long userId;
    private String firstName;
    private String secondName;
    private int userStateId;

    public UserLoggedDto(String message, String redirectPath, boolean authorized, Long userId, String firstName, String secondName, int userStateId) {
        this.message = message;
        this.redirectPath = redirectPath;
        this.authorized = authorized;
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userStateId = userStateId;
    }

    public UserLoggedDto(String message, boolean authorized) {
        this.message = message;
        this.authorized = authorized;
    }

    public UserLoggedDto(boolean error, String message, String redirectPath) {
        this.message = message;
        this.redirectPath = redirectPath;
        this.error = error;
    }

    public UserLoggedDto(boolean error, String message) {
        this.message = message;
        this.error = error;
    }
}
