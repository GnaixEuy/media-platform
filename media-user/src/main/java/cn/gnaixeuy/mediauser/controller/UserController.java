package cn.gnaixeuy.mediauser.controller;

import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import cn.gnaixeuy.mediauser.mapper.UserMapper;
import cn.gnaixeuy.mediauser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @GetMapping(value = {"/info/{id}"})
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseResult<UserVo> infoById(@PathVariable String id) {
        return ResponseResult.success(
                this.userMapper.dto2Vo(this.userService.getUserDtoByUserId(id))
        );
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
