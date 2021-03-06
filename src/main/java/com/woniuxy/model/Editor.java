package com.woniuxy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName("t_editor")
@ApiModel(value="Editor对象", description="")
public class Editor implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "编辑id,主键并自增")
        @TableId(value = "editor_id", type = IdType.AUTO)
    private Integer editorId;

        @ApiModelProperty(value = "电话,电话号码，用于登录")
        private String tel;

        @ApiModelProperty(value = "密码,密码，需要加密")
        private String password;

        @ApiModelProperty(value = "状态,1表示正常，0表示已经离职")
        private Integer status;


}
