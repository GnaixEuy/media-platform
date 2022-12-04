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
    private String nickname;
    private Date birth;
    private UserGender gender;
    private String lastLoginIp;
    private Date lastLoginTime;
    private String city;

    //个人简介
    private String bio = "cao";
    //头像
    private String portrait = "http://blog.gnaixeuy.cn/wp-content/uploads/2021/04/cropped-srchttp-img.zcool_.cn-community-013cac57adc7dc0000012e7e85cfe0.jpg@900w_1l_2o_100sh.jpgreferhttp-img.zcool_.cnapp2002sizef999910000qa80n0g0nfmtjpeg-192x192.jpeg";
    private String profession;
}
