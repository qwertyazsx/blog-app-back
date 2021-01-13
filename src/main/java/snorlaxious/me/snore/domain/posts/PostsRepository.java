package snorlaxious.me.snore.domain.posts;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostsRepository extends PagingAndSortingRepository<Posts, Long> {
    Optional<Posts> findFirstByOrderByCreateDateDesc();
    Page<Posts> findAllByOrderByUpdateDateDesc(Pageable pageable);
}
