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

/**
 * <p>
 * 
 * </p>
 *
 * @author migua
 * @since 2021-03-11
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_application")
@ApiModel(value="Application对象", description="")
public class Application implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "申请ID,主键自增")
        @TableId(value = "application_id", type = IdType.AUTO)
      private Integer applicationId;

      @ApiModelProperty(value = "申请类型")
      private String applicationType;

      @ApiModelProperty(value = "申请人ID")
      private Integer userId;

      @ApiModelProperty(value = "处理申请的编辑ID")
      private Integer editorId;

      @ApiModelProperty(value = "假如是申请新增书籍的话则有此列")
      private String bookName;

      @ApiModelProperty(value = "审核状态,0为审核中,1为审核成功,-1为审核失败")
      private Integer status;


}
