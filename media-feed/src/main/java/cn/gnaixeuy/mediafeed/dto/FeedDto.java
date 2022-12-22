package cn.gnaixeuy.mediafeed.dto;

import cn.gnaixeuy.mediacommon.dto.UserDto;
import cn.gnaixeuy.mediacommon.entity.Feed;
import cn.gnaixeuy.mediafile.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Feed} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto implements Serializable {
    private String id;
    private Date createdDateTime;
    private Date updatedDateTime;
    private UserDto createdBy;
    private UserDto updatedBy;
    @NotNull
    private FileDto file;
    @NotNull
    private FileDto cover;
    @NotNull
    private Integer type;
    @NotNull
    private Integer size;
    @NotNull
    private Integer width;
    @NotNull
    private Integer height;
    @NotNull
    private Integer duration;
    private String bio;
    private String device;
    private boolean locked;

}