package cn.gnaixeuy.mediacomment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedCommentDto {
    private String id;
    private Date dateTime; //时间
    private String commenterName; //评论者的名字
    private String commenterHeaderUrl; //评论者的头像
    private String commentContent; //评论内容
    private boolean commentLike; //评论是否点赞
    private Long commentLikeNumber; //评论点赞数量

}
