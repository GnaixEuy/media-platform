package cn.gnaixeuy.mediafile.service;

import cn.gnaixeuy.mediacommon.entity.File;
import cn.gnaixeuy.mediacommon.enums.Storage;
import cn.gnaixeuy.mediafile.dto.FileDto;
import cn.gnaixeuy.mediafile.dto.request.FileUploadRequest;
import cn.gnaixeuy.mediafile.vo.UploadTokenResponse;

import java.io.IOException;

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
public interface FileService {
    UploadTokenResponse initUpload(FileUploadRequest fileUploadRequest) throws IOException;

    FileDto finishUpload(String id);

    Storage getDefaultStorage();

    File getFileEntity(String id);

    File getFileEntityByKey(String key);

    File getFileEntityByName(String name);
}
