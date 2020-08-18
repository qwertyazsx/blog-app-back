package snorlaxious.me.snore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import snorlaxious.me.snore.domain.posts.Posts;

@AllArgsConstructor
@Getter
public class PostsResponseDto {
    private final Long post_no;
    private final String title;
    private final String content;

    public PostsResponseDto(Posts entity) {
        this.post_no = entity.getPostNo();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
