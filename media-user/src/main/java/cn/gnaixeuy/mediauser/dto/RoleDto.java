package cn.gnaixeuy.mediauser.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link cn.gnaixeuy.mediauser.entity.Role} entity
 */
@Data
public class RoleDto implements Serializable {
    private final String id;
    private final Date createdDateTime;
    private final Date updatedDateTime;
    private final String name;
    private final String title;
}