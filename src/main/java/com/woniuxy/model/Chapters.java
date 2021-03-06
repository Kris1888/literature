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
@TableName("t_chapters")
@ApiModel(value="Chapters对象", description="")
public class Chapters implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "章节id,主键并自增")
        @TableId(value = "chapter_id", type = IdType.AUTO)
    private Integer chapterId;

        @ApiModelProperty(value = "章节名称,章节名称，不能超过25个字")
        private String chapterName;

        @ApiModelProperty(value = "章节内容,章节中的内容，不能超过20000汉字")
        private String content;

        @ApiModelProperty(value = "字数,每一章节的字数")
        private Integer wordNumber;

        @ApiModelProperty(value = "书籍id,书籍表的主键，作为外键")
        private Integer bookId;

        @ApiModelProperty(value = "更新时间,系统获取更新的时间")
        private Date updateTime;

        @ApiModelProperty(value = "状态,该状态是标记该章节是否存在违法或违规情况，")
        private Integer status;


}
