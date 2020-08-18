package snore.me.blogappback.web;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import snore.me.blogappback.domain.posts.Posts;
import snore.me.blogappback.domain.posts.PostsRepository;
import snore.me.blogappback.web.dto.PostsSaveRequestDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void savePost() {
        String title = "테스트용 포스트 제목";
        String content = "테스트용 포스트 본문";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                            .title(title)
                                                            .content(content)
                                                            .build();
        String url = "http://localhost:" + port + "/api/v1/posts/save";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() > 0L);
        List<Posts> allPosts = postsRepository.findAll();
        assertEquals(allPosts.get(0).getTitle(), title);
        assertEquals(allPosts.get(0).getContent(), content);
    }
}
