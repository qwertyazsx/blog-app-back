package snorlaxious.me.snore.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePost() {
        String title = "테스트용 포스트 제목";
        String content = "테스트용 포스트 본문";
        postsRepository.save(Posts.builder()
                                  .title(title)
                                  .content(content)
                                  .build());
        Page<Posts> postsList = postsRepository.findAll(PageRequest.of(0, 100));
        Posts post = postsList.get().findFirst().get();
        assertEquals(post.getTitle(), title);
        assertEquals(post.getContent(), content);
    }
}
