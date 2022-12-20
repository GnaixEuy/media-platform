package cn.gnaixeuy.mediauser.service;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediauser.dto.UserDto;
import cn.gnaixeuy.mediauser.dto.request.UserInfoUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
public interface UserService {
    /**
     * 通过userid 获取userDto信息
     *
     * @param id userId
     * @return userDto
     */
    UserDto getUserDtoByUserId(String id);


    User getUserInfoByUserId(String id);


    UserDto updateUserInfoById(String id, UserInfoUpdateRequest userInfoUpdateRequest);

    Page<UserDto> search(Pageable pageable);

    UserDto lockUserById(String id);

    Boolean deleteUserById(String id);

    List<UserDto> searchByConditions(String type, String condition);
}
