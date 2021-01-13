package snorlaxious.me.snore.service.posts;

import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
                                    .orElseThrow(() -> new PostNotFoundException("해당 포스트가 없습니다. post_no: " + post_no));
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

    public Page<PostsResponseDto> findPostlistPage(Integer page) {
        return postsRepository.findAllByOrderByUpdateDateDesc(PageRequest.of(page, 20)).map(new Function<Posts, PostsResponseDto>() {
            @Override
            public PostsResponseDto apply(Posts entity) {
                return new PostsResponseDto(entity);
            }
        });
    }

	public Long getPostCount() {
		return postsRepository.count();
	}
}
