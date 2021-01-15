package snorlaxious.me.snore.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snorlaxious.me.snore.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String tags;

    @Builder
    public PostsSaveRequestDto(String title, String content, String tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Posts toEntity() {
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .build();
    }
}
