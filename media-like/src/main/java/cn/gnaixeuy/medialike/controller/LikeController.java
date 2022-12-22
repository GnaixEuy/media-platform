package cn.gnaixeuy.medialike.controller;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.medialike.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }
}
