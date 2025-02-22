package test.project.backend.data.dto;

import lombok.Data;
import test.project.backend.data.entity.Post;
import test.project.backend.data.entity.User;

@Data
public class PostResponseDto {

    private String title;

    private String content;

    private User user;

    public PostResponseDto(Post post) {
        title= post.getTitle();
        content = post.getContent();
        user = post.getUser();
    }
}
