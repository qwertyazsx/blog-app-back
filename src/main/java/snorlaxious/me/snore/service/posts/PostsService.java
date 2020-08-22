package snorlaxious.me.snore.service.posts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import snorlaxious.me.snore.domain.posts.Posts;
import snorlaxious.me.snore.domain.posts.PostsRepository;
import snorlaxious.me.snore.utils.PostNotFoundException;
import snorlaxious.me.snore.web.dto.PostsResponseDto;
import snorlaxious.me.snore.web.dto.PostsSaveRequestDto;
import snorlaxious.me.snore.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getPostNo();
    }

    @Transactional
    public Long update(Long post_no, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(post_no)
                                    .orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. post_no: " + post_no));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return post_no;
    }

    public PostsResponseDto findRecentPost() {
        Posts entity = postsRepository.findFirstByOrderByCreateDateDesc()
                                      .orElseThrow(() -> new PostNotFoundException("포스트가 없습니다."));
        return new PostsResponseDto(entity);
    }

    public PostsResponseDto findByPostNo(Long post_no) {
        Posts entity = postsRepository.findById(post_no)
                                      .orElseThrow(() -> new PostNotFoundException("해당 포스트가 없습니다. post_no: " + post_no));
        return new PostsResponseDto(entity);
    }
}
