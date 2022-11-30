package cn.gnaixeuy.mediafile.dto;

import cn.gnaixeuy.mediacommon.enums.FileType;
import cn.gnaixeuy.mediafile.enums.FileStatus;
import cn.gnaixeuy.mediafile.enums.Storage;
import lombok.Data;

import java.util.Date;

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
public class FileDto {
    private String id;

    private String name;

    private String key;

    private String uri;

    private String ext;

    private Long size;

    private FileType type;

    private Storage storage;

    private FileStatus status;

    private Date createdDateTime;

    private Date updatedDateTime;
}

