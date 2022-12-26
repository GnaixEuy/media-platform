package cn.gnaixeuy.medialike.service;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;

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
public interface LikeService {

    boolean likeById(String id, User user, boolean isLike);

    Long getFeedLikeNum(String id);

    Boolean getFeedIsLikeByFeedIdAndUserId(String userId, String feedId);

    List<UserVo> getLikeUserListByFeedId(String id);

}
