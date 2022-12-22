package cn.gnaixeuy.mediauaa.service;

import cn.gnaixeuy.mediacommon.dto.UserDto;
import cn.gnaixeuy.mediauaa.dto.RegisterByPhoneRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

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
public interface UserService extends UserDetailsService {

    /**
     * 获取去当前登录用户的信息
     *
     * @return user dto
     */
    UserDto getCurrentUserInfo();

    UserDto registerByPhone(RegisterByPhoneRequest registerByPhoneRequest);
}
