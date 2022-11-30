package cn.gnaixeuy.mediauaa.mapper;

import cn.gnaixeuy.mediacommon.dto.UserDto;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import cn.gnaixeuy.mediauaa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings(value = {@Mapping(source = "id", target = "id")})
    UserDto entity2Dto(User user);

    @Mappings(value = {
            @Mapping(target = "uid", source = "id"),
            @Mapping(target = "city", source = "userCity"),
            @Mapping(target = "nickname", source = "userNickname"),
            @Mapping(source = "userBirthday", target = "birth", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "gender", source = "userGender")
    })
    UserVo dto2Vo(UserDto userDto);

}
