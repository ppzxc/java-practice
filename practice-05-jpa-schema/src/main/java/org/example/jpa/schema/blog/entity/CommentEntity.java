package org.example.jpa.schema.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blog_comment")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 128)
    private String message;

    @Column(name = "writer", nullable = false, length = 128)
    private String writer;

    @ToString.Exclude
    @ManyToOne(targetEntity = PostEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ToString.Exclude
    @OneToMany(targetEntity = ReplyEntity.class, fetch = FetchType.LAZY, mappedBy = "comment", orphanRemoval = true)
    private Set<ReplyEntity> replies;
}
