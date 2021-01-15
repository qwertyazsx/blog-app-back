package snorlaxious.me.snore.service.posts;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import snorlaxious.me.snore.domain.posts.Posts;
import snorlaxious.me.snore.domain.posts.PostsRepository;
import snorlaxious.me.snore.domain.posts.PostsTag;
import snorlaxious.me.snore.domain.posts.PostsTagRepository;
import snorlaxious.me.snore.domain.posts.Tag;
import snorlaxious.me.snore.domain.posts.TagRepository;
import snorlaxious.me.snore.utils.PostNotFoundException;
import snorlaxious.me.snore.web.dto.PostsResponseDto;
import snorlaxious.me.snore.web.dto.PostsSaveRequestDto;
import snorlaxious.me.snore.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final TagRepository tagRepository;
    private final PostsTagRepository postsTagRepository;

    @Transactional
    public Long savePost(PostsSaveRequestDto requestDto) {
        Posts post = postsRepository.save(requestDto.toEntity());
        savePostsTag(post, parseAndSaveTag(requestDto.getTags()));
        return post.getPostNo();
    }

    @Transactional
    private List<Tag> parseAndSaveTag(String tags) {
        List<Tag> tagList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(tags, "# ");
        while (tokenizer.hasMoreTokens()) {
            Tag newTag = Tag.builder().name(tokenizer.nextToken()).build();
            Tag resultTag = tagRepository.findFirstByName(newTag.getName()).orElse(newTag); // 태그가 존재하면 사용, 없으면 새로운 태그 생성
            tagList.add(tagRepository.save(resultTag));
        }
        return tagList;
    }

    @Transactional
    private void savePostsTag(Posts post, List<Tag> tagList) {
        for (Tag tag : tagList) {
            postsTagRepository.save(PostsTag.builder().posts(post).tag(tag).build());
        }
    }

    @Transactional
    public Long updatePost(Long post_no, PostsUpdateRequestDto requestDto) {
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
