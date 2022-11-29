package cn.gnaixeuy.mediahello.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
public abstract class BaseEntity implements Serializable {

    private String id;

    private Date createdDateTime;

    private Date updatedDateTime;


}