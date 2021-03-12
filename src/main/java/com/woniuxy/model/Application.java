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
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_application")
@ApiModel(value="Application对象", description="")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "application_id", type = IdType.AUTO)
    private Integer applicationId;

    private Integer applicationType;

    private Integer userId;

    private Integer editorId;

    private String bookName;

    private Integer status;


}
