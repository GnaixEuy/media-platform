package cn.gnaixeuy.mediauser.service;

import cn.gnaixeuy.mediauser.dto.UserDto;

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
public interface UserService {
    /**
     * 通过userid 获取userDto信息
     *
     * @param id userId
     * @return userDto
     */
    UserDto getUserDtoByUserId(String id);

}
