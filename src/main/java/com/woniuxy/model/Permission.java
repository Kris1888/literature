package com.woniuxy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_permission")
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "权限id,主键并自增")
        @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

        @ApiModelProperty(value = "权限名")
        private String permissionName;

        @ApiModelProperty(value = "权限跳转页面")
        private String permissionUrl;

        @ApiModelProperty(value = "权限等级")
        private Integer permissionLevel;

        @ApiModelProperty(value = "父权限ID")
        private Integer pid;
        private ArrayList<Permission> childMenu;

}
