package cn.gnaixeuy.mediafeed.controller;

import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediafeed.dto.FeedDto;
import cn.gnaixeuy.mediafeed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping(value = "/page")
    public ResponseResult<Page<FeedDto>> getVideoListPage(@PageableDefault(sort = {"createdDateTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FeedDto> videoListPage = this.feedService.getVideoListPage(pageable);
        return ResponseResult.success(videoListPage);
    }

    @PutMapping(value = {"/lock/{id}"})
    public ResponseResult<String> lockVideoById(@PathVariable String id) {
        if (!this.feedService.lockVideoById(id)) {
            return ResponseResult.error("操作失败");
        }
        return ResponseResult.success("操作成功");
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseResult<String> deleteVideoById(@PathVariable String id) {
        if (!this.feedService.deleteVideoById(id)) {
            return ResponseResult.error("删除失败,联系管理员");
        }
        return ResponseResult.success("删除成功");
    }

    @GetMapping(value = {"/search/{type}/{input}"})
    public ResponseResult<List<FeedDto>> search(@PathVariable String type, @PathVariable String input) {
        return ResponseResult.success(this.feedService.adminSearch(type, input));
    }

    @Autowired
    public void setFeedService(FeedService feedService) {
        this.feedService = feedService;
    }

}
