package snorlaxious.me.snore.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String tags;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String tags) {
        this.title = title;
        this.content = content;
    }
}
