package snorlaxious.me.snore.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import snorlaxious.me.snore.service.posts.PostsService;
import snorlaxious.me.snore.web.dto.PostsResponseDto;
import snorlaxious.me.snore.web.dto.PostsSaveRequestDto;
import snorlaxious.me.snore.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsController {
    private final PostsService postsService;

    @PostMapping("/save")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/update/{postNo}")
    public Long update(@PathVariable Long postNo, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(postNo, requestDto);
    }

    @GetMapping("/recent")
    public PostsResponseDto findRecentPost() {
        return postsService.findRecentPost();
    }

    @GetMapping("/{postNo}")
    public PostsResponseDto findByPostNo(@PathVariable Long postNo) {
        return postsService.findByPostNo(postNo);
    }
}
