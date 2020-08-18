package snore.me.blogappback.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snore.me.blogappback.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .build();
    }
}
