package cn.gnaixeuy.mediacomment.service;

import cn.gnaixeuy.mediacomment.dto.AddCommentRequest;
import cn.gnaixeuy.mediacomment.dto.FeedCommentDto;

import java.util.List;

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
public interface FeedCommentService {
    boolean addComment(AddCommentRequest addCommentRequest);

    Long getCommentNumberByFeedId(String feedId);

    List<FeedCommentDto> getCommentListByFeedId(String id);

    boolean commentLike(String id);

    boolean deleteComment(String id);
}
