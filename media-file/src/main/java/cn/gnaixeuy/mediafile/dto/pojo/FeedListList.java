package cn.gnaixeuy.mediafile.dto.pojo;

import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedListList {

    private String id;
    private String type;
    private FeedListListContent content;
    //    FeedListListLocation location;
    private String device;
    private Integer aclType;
    private Integer commentType;
    private Date createdDateTime;
    private UserVo user;
    private Integer likeCount;
    private Integer commentCount;
    private Integer shareCount;
    private Integer viewCount;
    private boolean isFollow;
    private boolean isLike;

}
