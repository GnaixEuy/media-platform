package cn.gnaixeuy.mediafeed.entity;

import cn.gnaixeuy.mediacommon.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/30
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VideoContentAttachments extends BaseEntity {

    //TODO 待完成
    private String url;
    private String cover;
    private String gifCover;
    private Double duration;
    private Integer width;
    private Integer height;
    private String type;

}
