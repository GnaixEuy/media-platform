package cn.gnaixeuy.mediafeed.vo;


import cn.gnaixeuy.mediafeed.dto.pojo.FeedListList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class FeedListResponse {

    private List<FeedListList> list;
    private Integer cursor;
    private Integer count;
    private boolean hasMore;

}
