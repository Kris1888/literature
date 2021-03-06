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
@TableName("t_commit")
@ApiModel(value="Commit对象", description="")
public class Commit implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "评论id,主键并自增")
        @TableId(value = "commit_id", type = IdType.AUTO)
    private Integer commitId;

        @ApiModelProperty(value = "书籍id,书籍的外键")
        private Integer bookId;

        @ApiModelProperty(value = "评论内容,评论的内容，不要超过200个字")
        private String commitContent;

        @ApiModelProperty(value = "评论日期,评论的日期")
        private Date commitDate;

        @ApiModelProperty(value = "状态,评论的状态，0为普通，1为加精评论")
        private Integer status;


}
