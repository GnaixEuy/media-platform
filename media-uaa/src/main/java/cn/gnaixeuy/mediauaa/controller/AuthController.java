package cn.gnaixeuy.mediauaa.controller;

import cn.gnaixeuy.mediacommon.dto.UserDto;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediauaa.dto.RegisterByPhoneRequest;
import cn.gnaixeuy.mediauaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.*;

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
    private TokenEndpoint tokenEndpoint;

    @GetMapping(value = {"/getCurrentUserInfo"})
    public ResponseResult<UserDto> getCurrentUserInfo() {
        return ResponseResult.success(this.userService.getCurrentUserInfo());
    }

    @PostMapping(value = {"/register"})
    public ResponseResult<UserDto> registerByPhone(@RequestBody RegisterByPhoneRequest registerByPhoneRequest) {
        return ResponseResult.success(this.userService.registerByPhone(registerByPhoneRequest));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
