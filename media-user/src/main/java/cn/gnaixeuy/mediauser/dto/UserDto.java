package cn.gnaixeuy.mediauser.dto;

import cn.gnaixeuy.mediauser.enums.UserGender;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link cn.gnaixeuy.mediauser.entity.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final String id;
    private final Date createdDateTime;
    private final Date updatedDateTime;
    private final String userPhone;
    private final String userNickname;
    private final Date userBirthday;
    private final UserGender userGender;
    private final Boolean locked;
    private final Boolean enabled;
    private final String lastLoginIp;
    private final Date lastLoginTime;
    private final List<RoleDto> roles;
}