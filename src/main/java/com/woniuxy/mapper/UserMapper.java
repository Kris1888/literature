package com.woniuxy.mapper;

import com.woniuxy.dto.Result;
import com.woniuxy.model.Permission;
import com.woniuxy.model.User;
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
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT p.* " +
            "FROM t_user AS u " +
            "JOIN t_user_role AS ur " +
            "ON u.id=ur.uid " +
            "JOIN t_role AS r " +
            "ON ur.rid=r.id " +
            "JOIN t_role_permission AS rp " +
            "ON r.id=rp.rid " +
            "JOIN t_permission AS p " +
            "ON rp.pid=p.id " +
            "WHERE u.id=#{uid}")
    List<Permission> findMenusByUid(Integer uid);


    @Select("SELECT p.*" +
            "FROM t_user AS u" +
            "JOIN t_user_role AS ur" +
            "ON u.id=ur.uid" +
            "JOIN t_role AS r" +
            "ON ur.rid=r.id" +
            "JOIN t_role_permission AS rp" +
            "ON r.id=rp.rid" +
            "JOIN t_permission AS p" +
            "ON rp.pid=p.id" +
            "WHERE p.`level`=3" +
            "AND u.id=#{uid}")
    List<Permission> findButtonsByUid(Integer uid);
    @Delete("delete from t_user_role where uid=#{uid}")
    void deleteByUid(Integer uid);

    @Insert("insert into t_user_role(uid,rid) values(#{uid},#{rid})")
    void insertRoleByUidAndRid(@RequestParam Integer uid, @RequestParam Integer rid);
    //删除用户角色关联表的相关信息
}
