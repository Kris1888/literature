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
@TableName("t_book_data")
@ApiModel(value="BookData对象", description="")
public class BookData implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "数据id,主键，并自增")
        @TableId(value = "book_data_id", type = IdType.AUTO)
    private Integer bookDataId;

        @ApiModelProperty(value = "书籍,外键")
        private Integer bookId;

        @ApiModelProperty(value = "点击量,点击量，没有点击则记录为0")
        private Integer clickNumber;

        @ApiModelProperty(value = "收藏量,收藏量，没有点击就记为0")
        private Integer collection;

        @ApiModelProperty(value = "订阅量,订阅量")
        private Integer subscribe;

        @ApiModelProperty(value = "统计日期,统计的日期")
        private Date countDate;


}
