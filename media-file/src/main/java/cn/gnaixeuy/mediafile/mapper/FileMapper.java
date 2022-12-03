package cn.gnaixeuy.mediafile.mapper;

import cn.gnaixeuy.mediafile.dto.FileDto;
import cn.gnaixeuy.mediafile.dto.request.FileUploadRequest;
import cn.gnaixeuy.mediafile.entity.File;
import cn.gnaixeuy.mediafile.vo.FileVo;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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

@Mapper(componentModel = "spring")
@DecoratedWith(FileMapperDecorator.class)
public interface FileMapper {

    @Mappings(value = {
            @Mapping(target = "key", source = "key"),
            @Mapping(target = "ext", source = "ext"),
            @Mapping(target = "size", source = "size"),
            @Mapping(target = "name", source = "name"),
    })
    File createEntity(FileUploadRequest fileUploadRequest);

    FileVo toVo(FileDto fileDto);
    
    FileDto toDto(File file);

    File toEntity(FileDto fileDto);
}
