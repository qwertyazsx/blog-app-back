package snore.me.blogappback.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import snore.me.blogappback.service.posts.PostsService;
import snore.me.blogappback.web.dto.PostsSaveRequestDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsController {
    private final PostsService postsService;

    @PostMapping("/save")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
}
