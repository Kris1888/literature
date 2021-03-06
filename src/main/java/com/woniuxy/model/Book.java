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
@TableName("t_book")
@ApiModel(value="Book对象", description="")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "书籍id,主键，并自增")
        @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

        @ApiModelProperty(value = "书籍名称,书籍名称，不能超过20个字")
        private String bookName;

        @ApiModelProperty(value = "书籍封面,图片存储的路径，字符串形式")
        private String image;

        @ApiModelProperty(value = "书籍简介,书籍必须有简介，不能超过500字")
        private String description;

        @ApiModelProperty(value = "作者id,作者表中的主键作为外键")
        private Integer authorId;

        @ApiModelProperty(value = "分类id,分类表的主键作为外键")
        private Integer categoryId;

        @ApiModelProperty(value = "状态,状态分为两种：连载和完结，默认为连载")
        private String status;

        @ApiModelProperty(value = "总更新字数,总更新字数，默认为0")
        private Integer count;

        @ApiModelProperty(value = "连更天数,连续更新天数，如果是0则认为断更")
        private Integer countDay;

        @ApiModelProperty(value = "创建书籍时间,书籍的创建时间")
        private Date createTime;

        @ApiModelProperty(value = "最后更新时间,系统获取最后一次更新时间")
        private Date updateTime;

        @ApiModelProperty(value = "标记,标记书籍是否正常，正常值为1，如果出现违法或")
        private Integer flag;

        @ApiModelProperty(value = "订阅金额,如果不设置订阅金额，则默认为0，如果是0则")
        private Integer amont;


}
