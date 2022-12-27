package cn.gnaixeuy.mediauser.controller;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.PageVo;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import cn.gnaixeuy.mediauser.client.LikeFeignClient;
import cn.gnaixeuy.mediauser.dto.UserDto;
import cn.gnaixeuy.mediauser.dto.request.FollowRequest;
import cn.gnaixeuy.mediauser.dto.request.UserInfoUpdateRequest;
import cn.gnaixeuy.mediauser.mapper.UserMapper;
import cn.gnaixeuy.mediauser.service.UserService;
import cn.gnaixeuy.mediauser.vo.UserInfoExResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private LikeFeignClient likeFeignClient;

    @PostMapping(value = {"/follow"})
    public ResponseResult<String> followUserById(@RequestBody FollowRequest followRequest) {
        this.userService.follow(followRequest);
        return ResponseResult.success("关注结果修改成功");
    }

    @GetMapping(value = {"/isFollowRelation/{userId}/{followId}"})
    public ResponseResult<Boolean> isFollowRelation(@PathVariable String followId, @PathVariable String userId) {
        return ResponseResult.success(this.userService.isFollowRelation(userId, followId));
    }

    @GetMapping(value = {"/isMyFollow/{followId}"})
    public ResponseResult<Boolean> isMyFollow(@PathVariable String followId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseResult.success(this.userService.isFollowRelation(currentUser.getId(), followId));
    }

    @GetMapping(value = {"/me"})
    public ResponseResult<UserVo> me() {
        return ResponseResult.success(this.userMapper.dto2Vo(
                this.userService.getUserDtoByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
        ));
    }

    @GetMapping(value = {""})
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseResult<Page<UserVo>> search(@PageableDefault(sort = {"createdDateTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseResult.success(userService.search(pageable).map(userMapper::dto2Vo));
    }

    @PutMapping(value = {"/lock/{id}"})
    public ResponseResult<UserVo> lockUserById(@PathVariable String id) {
        UserDto userDto = this.userService.lockUserById(id);
        String message;
        if (userDto.getLocked()) {
            message = "封禁成功";
        } else {
            message = "解封成功";
        }
        ResponseResult<UserVo> userVoResponseResult = new ResponseResult<>();
        userVoResponseResult.setCode(200);
        userVoResponseResult.setData(this.userMapper.dto2Vo(userDto));
        userVoResponseResult.setMessage(message);
        return userVoResponseResult;
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseResult<String> deleteUserById(@PathVariable String id) {
        if (!this.userService.deleteUserById(id)) {
            return ResponseResult.error("删除失败");
        }
        return ResponseResult.success("删除成功");
    }


    @GetMapping(value = {"/info/{id}"})
    public ResponseResult<UserVo> infoById(@PathVariable String id) {
        return ResponseResult.success(this.userMapper.dto2Vo(this.userService.getUserDtoByUserId(id)));
    }

    @GetMapping(value = {"/search/{type}/{like}"})
    public ResponseResult<PageVo<UserVo>> searchUserInfoByIdOrNameOrPhone(@PathVariable String type, @PathVariable String like) {
        List<UserDto> userDtos = this.userService.searchByConditions(type, like);
        return ResponseResult.success(
                new PageVo<>(userDtos.stream().map(this.userMapper::dto2Vo).collect(Collectors.toList()), userDtos.size()));
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
        userInfoExResponse.setFollowerCount(this.userService.getFollowerNumber(userVo.getId()));
        userInfoExResponse.setFollowingCount(this.userService.getFollowingNumber(userVo.getId()));
        userInfoExResponse.setLikeCount(this.likeFeignClient.getMyLikeFeedNumber(userVo.getId()).getData());
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

    @Autowired
    public void setLikeFeignClient(LikeFeignClient likeFeignClient) {
        this.likeFeignClient = likeFeignClient;
    }
}
