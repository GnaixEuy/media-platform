package cn.gnaixeuy.mediacommon.vo.user;

import cn.gnaixeuy.mediacommon.enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/29
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private String id;
    private Date createdDateTime;
    private Date updatedDateTime;
    private String userPhone;
    private String userNickname;
    //    private String password;
    private Date userBirthday;
    private UserGender userGender;
    private Boolean locked;
    private Boolean enabled;
    private String lastLoginIp;
    private Date lastLoginTime;
}
