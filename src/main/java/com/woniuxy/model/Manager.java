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
@TableName("t_manager")
@ApiModel(value="Manager对象", description="")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "管理员id,主键并自增")
        @TableId(value = "manager_id", type = IdType.AUTO)
    private Integer managerId;

        @ApiModelProperty(value = "电话号码")
        private String managerTel;

        @ApiModelProperty(value = "密码,需要对密码进行非对称加密")
        private String password;

        @ApiModelProperty(value = "状态,该账号是否正常使用")
        private String status;


}
