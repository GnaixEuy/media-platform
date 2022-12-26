package cn.gnaixeuy.mediacomment.entity;

import cn.gnaixeuy.mediacommon.entity.BaseEntity;
import cn.gnaixeuy.mediacommon.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feed_comment")
public class FeedComment extends BaseEntity {

    @JoinColumn(name = "feed_id")
    private String feedId;

    @Size(max = 32)
    @Column(name = "parent_id", length = 32)
    private String parentId;

    @Size(max = 255)
    @Column(name = "details")
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

}