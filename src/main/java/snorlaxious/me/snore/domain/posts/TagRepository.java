package snorlaxious.me.snore.domain.posts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
	Optional<Tag> findFirstByName(String name);
}
