package snorlaxious.me.snore.domain.posts;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostsRepository extends PagingAndSortingRepository<Posts, Long> {
    Optional<Posts> findFirstByOrderByCreateDateDesc();
}
