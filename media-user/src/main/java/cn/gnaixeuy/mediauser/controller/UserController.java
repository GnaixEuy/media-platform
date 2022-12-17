package cn.gnaixeuy.mediauser.controller;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import cn.gnaixeuy.mediauser.dto.request.UserInfoUpdateRequest;
import cn.gnaixeuy.mediauser.mapper.UserMapper;
import cn.gnaixeuy.mediauser.service.UserService;
import cn.gnaixeuy.mediauser.vo.UserInfoExResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = {"/me"})
    public ResponseResult<UserVo> me() {
        return ResponseResult.success(this.userMapper.dto2Vo(
                this.userService.getUserDtoByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
        ));
    }

    @GetMapping(value = {"/info/{id}"})
    public ResponseResult<UserVo> infoById(@PathVariable String id) {
        return ResponseResult.success(this.userMapper.dto2Vo(this.userService.getUserDtoByUserId(id)));
    }


    @PutMapping(value = {"/info/{id}"})
    public ResponseResult<UserVo> updateInfo(@PathVariable String id, @RequestBody UserInfoUpdateRequest userInfoUpdateRequest) {
        return ResponseResult.success(this.userMapper.dto2Vo(this.userService.updateUserInfoById(id, userInfoUpdateRequest)));
    }

    @GetMapping(value = {"/info/entity/{id}"})
    public ResponseResult<User> getEntityInfoById(@PathVariable String id) {
        return ResponseResult.success(this.userService.getUserInfoByUserId(id));
    }

    @GetMapping(value = {"/info/detail/{id}"})
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseResult<UserInfoExResponse> detailInfoById(@PathVariable String id) {
        UserVo userVo = this.userMapper.dto2Vo(this.userService.getUserDtoByUserId(id));
        UserInfoExResponse userInfoExResponse = new UserInfoExResponse();
        userInfoExResponse.setUser(userVo);
        return ResponseResult.success(userInfoExResponse);
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
