package snorlaxious.me.snore.domain.posts;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<Posts> postsList = postsRepository.findAll();
        Posts post = postsList.get(0);
        assertEquals(post.getTitle(), title);
        assertEquals(post.getContent(), content);
    }
}
