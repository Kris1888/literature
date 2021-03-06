package com.woniuxy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("t_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键，并自增")
        @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

        @ApiModelProperty(value = "如果用户没填写，系统自动生成一个")
        private String userName;

        @ApiModelProperty(value = "需要对密码加密，不能明文")
        private String password;

        @ApiModelProperty(value = "默认值为0")
        private Integer account;

        @ApiModelProperty(value = "电话号码不能为null，注册需要使用电话号码")
        private String userTel;

        @ApiModelProperty(value = "邮箱可以为null")
        private String email;

        @ApiModelProperty(value = "如果用户没有填写，默认保密")
        private String sex;

    private String qq;

        @ApiModelProperty(value = "可以为null")
        private Date birthday;

        @ApiModelProperty(value = "直接获取系统的时间")
        private Date registerTime;

        @ApiModelProperty(value = "直接获取系统时间")
        private Date lastLogin;

        @ApiModelProperty(value = "可以为null")
        private String description;

        @ApiModelProperty(value = "默认为0,是作者改为1")
        @TableField("isAuthor")
    private Integer isAuthor;

        @ApiModelProperty(value = "笔名长度不能超过10个汉字，不能出现相同的笔名")
        private String penName;

        @ApiModelProperty(value = "如果没有稿酬，默认值为0")
        private Integer payment;

        @ApiModelProperty(value = "作者所属的编辑")
        private Integer editorId;

        @ApiModelProperty(value = "系统获取申请成功成为作者的时间")
        private Date authorTime;

        @ApiModelProperty(value = "可以为null")
        private String name;

        @ApiModelProperty(value = "可以为null")
        private String address;

        @ApiModelProperty(value = "根据身份证判断是否已经成年，对身份证号码需")
        @TableField("isCard")
    private String isCard;

        @ApiModelProperty(value = "用于结算稿费")
        @TableField("bankCard")
    private String bankCard;

        @ApiModelProperty(value = "默认为1等级，这里显示的是作者的等级，根据作")
        private String degree;

        @ApiModelProperty(value = "默认为正常，如果作者出现违法或违规，则立即冻结该作者的所有稿酬")
        private String status;


}
