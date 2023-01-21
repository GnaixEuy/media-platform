package cn.gnaixeuy.mediacomment.controller;

import cn.gnaixeuy.mediacomment.dto.AddCommentRequest;
import cn.gnaixeuy.mediacomment.dto.FeedCommentDto;
import cn.gnaixeuy.mediacomment.service.FeedCommentService;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value = {"/like/{id}"})
    public ResponseResult<String> commentLike(@PathVariable String id) {
        System.out.println(id);
        this.feedCommentService.commentLike(id);
        return null;
    }

    @GetMapping(value = {"/getCommentList/{id}"})
    public ResponseResult<Map<String, List<FeedCommentDto>>> getCommentListByFeedId(@PathVariable String id) {
        List<FeedCommentDto> commentListByFeedId = this.feedCommentService.getCommentListByFeedId(id);
        commentListByFeedId.forEach(System.out::println);
        Map<String, List<FeedCommentDto>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("commentList", commentListByFeedId);
        return ResponseResult.success(stringListHashMap);
    }

    @GetMapping(value = {"/getNumber/{feedId}"})
    public ResponseResult<Long> getCommentNumberByFeedId(@PathVariable String feedId) {
        return ResponseResult.success(this.feedCommentService.getCommentNumberByFeedId(feedId));
    }

    @DeleteMapping(value = {"/deleteComment/{id}"})
    public ResponseResult<String> deleteComment(@PathVariable String id) {
        this.feedCommentService.deleteComment(id);
        return ResponseResult.success("删除成功");
    }

    @Autowired
    public void setFeedCommentService(FeedCommentService feedCommentService) {
        this.feedCommentService = feedCommentService;
    }

}
