package cn.gnaixeuy.mediafile.service;

import cn.gnaixeuy.mediafile.dto.FileUploadDto;

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
public interface StorageService {
    FileUploadDto initFileUpload(String hashCode) throws IOException;

    String getFileUri(String fileKey);

}

