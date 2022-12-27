package cn.gnaixeuy.mediauser.entity;

import cn.gnaixeuy.mediacommon.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_follow")
public class UserFollow extends BaseEntity {

    @NotNull
    @Column(name = "follow_id", nullable = false)
    private String followId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

}