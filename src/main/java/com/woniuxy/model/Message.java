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
 * @since 2021-03-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("t_message")
@ApiModel(value="Message对象", description="")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "消息的ID,主键自增")
        @TableId(value = "message_id", type = IdType.AUTO)
      private Integer messageId;

      @ApiModelProperty(value = "消息的名称")
      private String messageName;

      @ApiModelProperty(value = "消息的内容")
      private String messageContent;

      @ApiModelProperty(value = "消息所属用户的ID")
      private Integer userId;


}
