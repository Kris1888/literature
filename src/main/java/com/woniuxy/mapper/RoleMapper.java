package com.woniuxy.mapper;

import com.woniuxy.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT r.rolename" +
            "FROM t_user u" +
            "JOIN t_user_role ur" +
            "ON u.id=ur.uid" +
            "JOIN t_role r" +
            "ON ur.rid=r.id" +
            "WHERE u.id=#{uid}")
    List<String> findRolesByUid(Integer uid);

    @Delete("DELETE FROM t_role_permission WHERE rid=#{rid}")
    void deletePermissionByRid(Integer rid);

    @Insert("insert into t_role_permission(rid,pid) values(#{rid},#{pid})")
    void insertPermissionByRidAndPid(@RequestParam Integer rid, @RequestParam Integer pid);

}
