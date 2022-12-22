package cn.gnaixeuy.mediafeed.service;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediafeed.dto.FeedDto;
import cn.gnaixeuy.mediafeed.dto.request.PublishFeedRequest;
import cn.gnaixeuy.mediafeed.vo.FeedListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/2
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public interface FeedService {

    /**
     * 发布视屏请求
     *
     * @param publishFeedRequest 视屏请求
     * @return 待定
     */
    FeedDto publicVideoFeed(PublishFeedRequest publishFeedRequest);

    FeedListResponse getHot(Integer cursor, Integer count);

    FeedListResponse getUserWorksList(User createdBy, Integer cursor, Integer count);

    Page<FeedDto> getVideoListPage(Pageable pageable);

    boolean lockVideoById(String id);

    boolean deleteVideoById(String id);
}
