package cn.gnaixeuy.mediafile.dto.request;

import cn.gnaixeuy.mediafile.dto.pojo.PublishFeedContent;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishFeedRequest {

    private Integer type = 0;

    private PublishFeedContent content;

    //TODO 发布地址
//    private PublishFeedLocation location;
    private String device;
    private Integer aclType;
    private Integer commentType;

}
