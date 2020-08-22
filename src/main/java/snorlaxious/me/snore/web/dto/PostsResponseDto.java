package snorlaxious.me.snore.web.dto;

import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import snorlaxious.me.snore.domain.posts.Posts;

@AllArgsConstructor
@Getter
public class PostsResponseDto {
    private final Long post_no;
    private final String title;
    private final String content;
    private final String createDate;
    private final String updateDate;

    public PostsResponseDto(Posts entity) {
        this.post_no = entity.getPostNo();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 HH:mm:ss");

        this.createDate = entity.getCreateDate().format(dateTimeFormatter);
        this.updateDate = entity.getUpdateDate().format(dateTimeFormatter);
    }
}
