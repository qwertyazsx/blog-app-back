package snorlaxious.me.snore.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import snorlaxious.me.snore.service.posts.PostsService;
import snorlaxious.me.snore.utils.InvalidPageNumberException;
import snorlaxious.me.snore.web.dto.PostsResponseDto;
import snorlaxious.me.snore.web.dto.PostsSaveRequestDto;
import snorlaxious.me.snore.web.dto.PostsUpdateRequestDto;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsController {
    private final PostsService postsService;

    @PostMapping("/save")
    public Long savePost(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.savePost(requestDto);
    }

    @PutMapping("/update/{postNo}")
    public Long updatePost(@PathVariable Long postNo, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.updatePost(postNo, requestDto);
    }

    @GetMapping("/{postNo}")
    public PostsResponseDto findByPostNo(@PathVariable Long postNo) {
        return postsService.findByPostNo(postNo);
    }

    @GetMapping("/recent/{page}")
    public Page<PostsResponseDto> findRecentPost(@PathVariable Integer page) {
        if (page < 1) throw new InvalidPageNumberException("페이지 번호가 유효하지 않습니다. : " + page);
        // TODO: 포스트 번호 받아서 이후 포스트만 전송
        return postsService.findPostlistPage(page - 1, 3);
    }

    @GetMapping("/list/{page}")
    public Page<PostsResponseDto> findPostlistPage(@PathVariable Integer page) {
        if (page < 1) throw new InvalidPageNumberException("페이지 번호가 유효하지 않습니다. : " + page);
        return postsService.findPostlistPage(page - 1, 10);
    }

    @GetMapping("/count")
    public Long getPostCount() {
        return postsService.getPostCount();
    }

    // TODO: 전체 태그 포스트 수로 정렬해서 응답하는 api 추가
    // TODO: 태그별 검색 추가

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @ExceptionHandler(InvalidPageNumberException.class)
    public Map<String, String> handleInvalidPageNumberException(InvalidPageNumberException e) {
        log.error(e.getMessage(), e);
        Map<String, String> errorAttributes = new HashMap<>();
        errorAttributes.put("code", "INVALID_PAGE_NUMBER");
        errorAttributes.put("message", e.getMessage());
        return errorAttributes;
    }
}
