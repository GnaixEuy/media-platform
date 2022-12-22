package cn.gnaixeuy.mediafeed.mapper;

import cn.gnaixeuy.mediacommon.entity.Feed;
import cn.gnaixeuy.mediafeed.dto.FeedDto;
import cn.gnaixeuy.mediafeed.vo.FeedVo;
import org.mapstruct.*;

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
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FeedMapper {
    Feed feedDtoToFeed(FeedDto feedDto);

    FeedDto feedToFeedDto(Feed feed);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Feed updateFeedFromFeedDto(FeedDto feedDto, @MappingTarget Feed feed);


    @Mappings(value = {
            @Mapping(target = "url", source = "file.uri"),
            @Mapping(target = "cover", source = "cover.uri"),
            @Mapping(target = "gifCover", source = "cover.uri")
    })
    FeedVo dto2Vo(FeedDto feedDto);
}
