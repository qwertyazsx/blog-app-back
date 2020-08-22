package snorlaxious.me.snore.domain.posts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findFirstByOrderByCreateDateDesc();
}
