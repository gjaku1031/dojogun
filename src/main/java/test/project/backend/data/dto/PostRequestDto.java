package test.project.backend.data.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import test.project.backend.data.entity.User;

@Data
public class PostRequestDto {

    @NotEmpty(message = "필수 입력값")
    private String title;

    @NotEmpty(message = "필수 입력값")
    private String content;

    private User user;
}
