package cn.gnaixeuy.mediauser.mapper;

import cn.gnaixeuy.mediauser.dto.UserDto;
import cn.gnaixeuy.mediauser.entity.User;
import org.mapstruct.Mapper;

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
@Mapper
public interface UserMapper {

    UserDto entity2Dto(User user);

}
