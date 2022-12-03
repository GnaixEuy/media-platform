package cn.gnaixeuy.mediafile.mapper;

import cn.gnaixeuy.mediafile.dto.FileDto;
import cn.gnaixeuy.mediafile.entity.File;
import cn.gnaixeuy.mediafile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

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
public abstract class FileMapperDecorator implements FileMapper {

    @Autowired
    @Qualifier("delegate")
    private FileMapper delegate;

    @Autowired
    private Map<String, StorageService> storageServices;

    @Override
    public FileDto toDto(File file) {
        FileDto fileDto = delegate.toDto(file);
        if (fileDto == null) {
            return null;
        }
        if (fileDto.getStorage() == null) {
            return null;
        }
        fileDto.setUri(storageServices.get(fileDto.getStorage().name()).getFileUri(fileDto.getKey()));
        return fileDto;
    }
}
