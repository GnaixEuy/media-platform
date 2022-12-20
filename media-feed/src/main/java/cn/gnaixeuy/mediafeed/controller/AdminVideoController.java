package cn.gnaixeuy.mediafeed.controller;

import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediafeed.client.UserFeignClient;
import cn.gnaixeuy.mediafeed.dto.FeedDto;
import cn.gnaixeuy.mediafeed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/20
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = "/feed/admin")
public class AdminVideoController {
    private FeedService feedService;
    private UserFeignClient userFeignClient;


    @GetMapping(value = "/page")
    public ResponseResult<Page<FeedDto>> getVideoListPage(@PageableDefault(sort = {"createdDateTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FeedDto> videoListPage = this.feedService.getVideoListPage(pageable);
//        System.out.println(videoListPage.getContent());
        return ResponseResult.success(videoListPage);
    }


    @Autowired
    public void setFeedService(FeedService feedService) {
        this.feedService = feedService;
    }

    @Autowired
    public void setUserFeignClient(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

}
