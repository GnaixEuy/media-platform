package cn.gnaixeuy.medialike.entity;

import cn.gnaixeuy.mediacommon.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feed_like")
public class Like extends BaseEntity {

    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;

    @NotNull
    @JoinColumn(name = "feed_id", nullable = false)
    private String feedId;

}