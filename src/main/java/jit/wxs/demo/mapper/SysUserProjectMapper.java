package jit.wxs.demo.mapper;

import jit.wxs.demo.entity.SysUserProject;
import jit.wxs.demo.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserProjectMapper {
    @Select("SELECT * FROM sys_user_project WHERE user_id = #{userId}")
    List<SysUserProject> listByUserId(Integer userId);
}
