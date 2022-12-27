package cn.gnaixeuy.mediauser.dto.request;

import cn.gnaixeuy.mediacommon.enums.UserGender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/4
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequest {

    private String id;
    private String nickname;
    private String portrait;
    private String bio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private UserGender gender;
    private String city;
    private String profession;
}
