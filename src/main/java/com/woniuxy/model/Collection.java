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
@TableName("t_collection")
@ApiModel(value="Collection对象", description="")
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "收藏id,主键并自增")
        @TableId(value = "collection_id", type = IdType.AUTO)
    private Integer collectionId;

        @ApiModelProperty(value = "用户id,收藏用户的id")
        private Integer userId;

        @ApiModelProperty(value = "书籍id,收藏书籍的id")
        private Integer bookId;

        @ApiModelProperty(value = "收藏时间,添加收藏的时间")
        private Date collectionDate;


}
