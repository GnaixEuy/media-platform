package cn.gnaixeuy.mediacomment.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "feed_comment_like")
public class FeedCommentLike {
    @Id
    @Size(max = 32)
    @Column(name = "id", nullable = false, length = 32)
    private String id;
    @JoinColumn(name = "comment_id")
    private String commentId;
    @JoinColumn(name = "user_id")
    private String userId;


}