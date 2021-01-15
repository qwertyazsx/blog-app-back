package snorlaxious.me.snore.domain.posts;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagNo;
    @Column(length = 20, nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<PostsTag> postsTag;

    @Builder
    public Tag(String name) {
        this.name = name;
    }
}
