package cn.gnaixeuy.medialike.controller;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import cn.gnaixeuy.medialike.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/like"})
public class LikeController {

    private LikeService likeService;

    @PutMapping(value = {"/feed/{id}/{isLike}"})
    public ResponseResult<String> likeFeedById(@PathVariable String id, @PathVariable String isLike) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (this.likeService.likeById(id, currentUser, Boolean.parseBoolean(isLike))) {
            return ResponseResult.success("修改点赞状态成功");
        } else {
            return ResponseResult.error("修改点赞状态失败");
        }
    }

    @GetMapping(value = {"/feed/likeThePeopleListByFeedId/{id}"})
    public ResponseResult<List<UserVo>> likeThePeopleListByFeedId(@PathVariable String id) {
        return ResponseResult.success(this.likeService.getLikeUserListByFeedId(id));
    }

    @GetMapping(value = {"/feed/likeNum/{id}"})
    public ResponseResult<Long> getFeedLikeNumByFeedId(@PathVariable String id) {
        return ResponseResult.success(this.likeService.getFeedLikeNum(id));
    }

    @GetMapping(value = {"/feed/isLike/{userId}/{feedId}"})
    public ResponseResult<Boolean> getFeedIsLikeByFeedIdAndUserId(@PathVariable String userId, @PathVariable String feedId) {
        return ResponseResult.success(this.likeService.getFeedIsLikeByFeedIdAndUserId(userId, feedId));
    }

    @GetMapping(value = {"/feed/likedUserIds/{feedId}"})
    public ResponseResult<List<String>> getLikedUserIdsByFeedId(@PathVariable String feedId) {
        return null;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

}
