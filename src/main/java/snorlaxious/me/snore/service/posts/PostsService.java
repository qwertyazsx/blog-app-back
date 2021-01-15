package snorlaxious.me.snore.service.posts;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Long updatePost(Long post_no, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(post_no)
                                    .orElseThrow(() -> new PostNotFoundException("해당 포스트가 없습니다. post_no: " + post_no));
        post.update(requestDto.getTitle(), requestDto.getContent());
        deletePostsTag(post);
        savePostsTag(post, parseAndSaveTag(requestDto.getTags()));
        return post_no;
    }

    @Transactional
    private List<Tag> parseAndSaveTag(String tags) {
        // TODO: Tag validator 생성: 길이 / 특수문자
        List<Tag> tagList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(tags, "# ");
        while (tokenizer.hasMoreTokens()) {
            Tag newTag = Tag.builder().name(tokenizer.nextToken()).build();
            Tag resultTag = tagRepository.findFirstByName(newTag.getName()).orElse(newTag); // 태그가 존재하면 사용, 없으면 새로운 태그 생성
            tagList.add(tagRepository.save(resultTag));
        }
        List<Tag> resultTagList = new ArrayList<>();
        // 중복 제거 및 최대 갯수 제한
        for (Tag tag : tagList) {
            if (resultTagList.size() >= 30)
                break;
            if (!resultTagList.contains(tag))
                resultTagList.add(tag);
        }
        return resultTagList;
    }

    @Transactional
    private void savePostsTag(Posts post, List<Tag> tagList) {
        for (Tag tag : tagList) {
            postsTagRepository.save(PostsTag.builder().posts(post).tag(tag).build());
        }
    }

    @Transactional
    private void deletePostsTag(Posts post) {
        postsTagRepository.deleteAllByPosts(post);
    }

    public PostsResponseDto findRecentPost() {
        Posts entity = postsRepository.findFirstByOrderByCreateDateDesc()
                                      .orElseThrow(() -> new PostNotFoundException("포스트가 없습니다."));
        return makeResponseDtoWithTags(entity);
    }

    public PostsResponseDto findByPostNo(Long post_no) {
        Posts entity = postsRepository.findById(post_no)
                                      .orElseThrow(() -> new PostNotFoundException("해당 포스트가 없습니다. post_no: " + post_no));
        return makeResponseDtoWithTags(entity);
    }

    public Page<PostsResponseDto> findPostlistPage(Integer page) {
        return postsRepository.findAllByOrderByUpdateDateDesc(PageRequest.of(page, 20)).map(new Function<Posts, PostsResponseDto>() {
            @Override
            public PostsResponseDto apply(Posts entity) {
                return makeResponseDtoWithTags(entity);
            }
        });
    }

    private PostsResponseDto makeResponseDtoWithTags(Posts entity) {
        List<String> tagList = entity.getPostsTags()
                                     .stream()
                                     .map(pt -> pt.getTag().getName())
                                     .collect(Collectors.toList());
        return PostsResponseDto.builder()
                               .entity(entity)
                               .tags(tagList)
                               .build();
    }

	public Long getPostCount() {
		return postsRepository.count();
	}
}
