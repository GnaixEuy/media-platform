package cn.gnaixeuy.mediacomment.mapper;

import cn.gnaixeuy.mediacomment.dto.FeedCommentDto;
import cn.gnaixeuy.mediacomment.entity.FeedComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/26
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Mapper(componentModel = "spring")
public interface FeedCommentMapper {

    @Mappings(value = {
            @Mapping(source = "createdDateTime", target = "dateTime", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "creator.userNickname", target = "commenterName"),
            @Mapping(source = "creator.portrait", target = "commenterHeaderUrl"),
            @Mapping(source = "details", target = "commentContent"),
    })
    FeedCommentDto entity2Dto(FeedComment feedComment);

}
