package cn.gnaixeuy.mediafile.controller;

import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediafile.dto.FeedDto;
import cn.gnaixeuy.mediafile.dto.request.PublishFeedRequest;
import cn.gnaixeuy.mediafile.service.FeedService;
import cn.gnaixeuy.mediafile.vo.FeedListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/4
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/feed"})
public class FeedController {

    private FeedService feedService;

    @PostMapping(value = {"/publish"})
    public ResponseResult<FeedDto> publicFeed(@RequestBody PublishFeedRequest publishFeedRequest) {
        return ResponseResult.success(this.feedService.publicVideoFeed(publishFeedRequest));
    }

    @GetMapping(value = {"/hot/{cursor}/{count}"})
    public ResponseResult<FeedListResponse> getHotFeedPage(@PathVariable Integer cursor, @PathVariable Integer count) {
        return ResponseResult.success(this.feedService.getHot(cursor, count));
    }

    @Autowired
    public void setFeedService(FeedService feedService) {
        this.feedService = feedService;
    }

}
