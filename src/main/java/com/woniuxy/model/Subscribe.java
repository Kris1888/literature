package com.woniuxy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("t_subscribe")
@ApiModel(value="Subscribe对象", description="")
public class Subscribe implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "订阅id,主键并自增")
        @TableId(value = "subscribe_id", type = IdType.AUTO)
    private Integer subscribeId;

        @ApiModelProperty(value = "书籍id,外键")
        private Integer bookId;

        @ApiModelProperty(value = "订阅金额,订阅花费的金额")
        private Integer amont;

        @ApiModelProperty(value = "用户id,定于的用户")
        private Integer userId;

        @ApiModelProperty(value = "订阅时间,定于的时间")
        private Date subscribDate;


}
