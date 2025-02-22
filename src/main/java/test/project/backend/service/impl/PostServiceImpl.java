package test.project.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.project.backend.data.dto.PostRequestDto;
import test.project.backend.data.dto.PostResponseDto;
import test.project.backend.data.entity.Post;
import test.project.backend.data.entity.User;
import test.project.backend.data.repository.PostRepository;
import test.project.backend.data.repository.UserRepository;
import test.project.backend.service.PostService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post createPost(PostRequestDto postRequestDto) {

        User user = userRepository.findById(postRequestDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        //자동으로 pk 생성
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        User user = userRepository.findById(postRequestDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        if (!post.getUser().equals(user)) {
            throw new IllegalArgumentException("본인 게시글만 수정 가능");
        }

        post.update(postRequestDto.getTitle(), postRequestDto.getContent());
    }



    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}