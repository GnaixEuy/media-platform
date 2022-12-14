package cn.gnaixeuy.mediacommon.entity;

import cn.gnaixeuy.mediacommon.enums.UserGender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity {

    @Column(unique = true)
    private String userPhone;

    private String userNickname;

    private String password;

    private Date userBirthday;

    @Enumerated(EnumType.ORDINAL)
    private UserGender userGender;

    private Boolean locked = false;

    private Boolean enabled = true;

    private String userCity = "默认城市";

    @Column(name = "user_profession")
    private String profession;
    @Column(name = "user_bio")
    private String bio = "快来介绍你自己吧！";
    @Column(name = "user_portrait")
    private String portrait = "默认头像";
    private String lastLoginIp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ", timezone = "GMT+8")
    private Date lastLoginTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}