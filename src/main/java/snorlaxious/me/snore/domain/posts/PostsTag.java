package snorlaxious.me.snore.domain.posts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;

@Entity
public class PostsTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_no")
    private Posts posts;
    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;

    @Builder
    public PostsTag(Posts posts, Tag tag) {
        this.posts = posts;
        this.tag = tag;
    }
}
