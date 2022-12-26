package cn.gnaixeuy.mediacomment.controller;

import cn.gnaixeuy.mediacomment.dto.AddCommentRequest;
import cn.gnaixeuy.mediacomment.service.FeedCommentService;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/26
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/comment"})
public class CommentController {

    private FeedCommentService feedCommentService;

    @PostMapping(value = {"/add"})
    public ResponseResult<String> addComment(@RequestBody AddCommentRequest addCommentRequest) {
        if (this.feedCommentService.addComment(addCommentRequest)) {
            return ResponseResult.success("评论成功");
        }
        return ResponseResult.error("评论失败");
    }

    @GetMapping(value = {"/getNumber/{feedId}"})
    public ResponseResult<Long> getCommentNumberByFeedId(@PathVariable String feedId) {
        return ResponseResult.success(this.feedCommentService.getCommentNumberByFeedId(feedId));
    }

    @Autowired
    public void setFeedCommentService(FeedCommentService feedCommentService) {
        this.feedCommentService = feedCommentService;
    }
}
