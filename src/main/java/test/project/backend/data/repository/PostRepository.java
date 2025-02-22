package test.project.backend.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.project.backend.data.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
