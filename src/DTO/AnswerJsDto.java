package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AnswerJsDto implements Serializable {
    private String message;
    private String redirect;

    public AnswerJsDto(String message) {
        this.message = message;
    }
}
