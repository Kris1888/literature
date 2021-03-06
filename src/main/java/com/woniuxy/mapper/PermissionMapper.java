package com.woniuxy.mapper;

import com.woniuxy.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT p.id" +
            "FROM t_role_permission rp" +
            "JOIN t_permission p" +
            "ON rp.pid=p.id" +
            "WHERE rp.rid=#{rid}" +
            "AND p.`level`=3")
    List<Integer> getPermissionsIdByRid(Integer rid);

    @Select("SELECT *" +
            "FROM t_permission" +
            "WHERE id=(" +
            "SELECT p.pid" +
            "FROM t_permission p" +
            "WHERE p.id=#{pid}+" +
            ")")
    Permission getPermissionByPid(Integer pid);

}
