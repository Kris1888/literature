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
@TableName("t_category")
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "分类id,主键，并自增")
        @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

        @ApiModelProperty(value = "分类名,分类的名称，比如：都市言情等")
        private String categoryName;

        @ApiModelProperty(value = "添加时间,系统获取添加该分类的时间")
        private Date addTime;


}
