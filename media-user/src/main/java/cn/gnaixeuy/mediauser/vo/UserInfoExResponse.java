package cn.gnaixeuy.mediauser.vo;

import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/30
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoExResponse {

    private UserVo user;
    private Integer followerCount = 0;
    private Integer followingCount = 0;
    private Integer likeCount = 0;
    private String relation;

}
