package cn.gnaixeuy.mediafile.vo;

import cn.gnaixeuy.mediacommon.enums.FileStatus;
import cn.gnaixeuy.mediacommon.enums.FileType;
import cn.gnaixeuy.mediacommon.enums.Storage;
import cn.gnaixeuy.mediacommon.vo.BaseVo;
import lombok.Data;

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
public class FileVo extends BaseVo {
    private String name;

    private String key;

    private String uri;

    private Storage storage;

    private String ext;

    private Long size;

    private FileType type;

    private FileStatus status;
}

