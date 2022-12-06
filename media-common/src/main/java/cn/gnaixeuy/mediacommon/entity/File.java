package cn.gnaixeuy.mediacommon.entity;

import cn.gnaixeuy.mediacommon.enums.FileStatus;
import cn.gnaixeuy.mediacommon.enums.FileType;
import cn.gnaixeuy.mediacommon.enums.Storage;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
public class File extends TraceableBaseEntity {

    private String name;

    @Column(name = "file_key")
    private String key;

    private String ext;

    private Integer size;

    @Enumerated(EnumType.ORDINAL)
    private FileType type;

    @Enumerated(EnumType.STRING)
    private Storage storage;

    @Enumerated(EnumType.ORDINAL)
    private FileStatus status = FileStatus.UPLOADING;

}
