package snorlaxious.me.snore.service.posts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import snorlaxious.me.snore.domain.posts.PostsRepository;
import snorlaxious.me.snore.web.dto.PostsSaveRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getPost_no();
    }
}
