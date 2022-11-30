package cn.gnaixeuy.mediauaa.controller;

import cn.gnaixeuy.mediacommon.dto.UserDto;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediauaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    private UserService userService;

    @GetMapping(value = {"/getCurrentUserInfo"})
    public ResponseResult<UserDto> getCurrentUserInfo() {
        return ResponseResult.success(this.userService.getCurrentUserInfo());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
