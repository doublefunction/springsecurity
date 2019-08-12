package jit.wxs.demo.service;

import jit.wxs.demo.entity.SysRole;
import jit.wxs.demo.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole getById(Integer id) {
        return roleMapper.selectById(id);
    }

    public SysRole getByName(String roleName) {
        return roleMapper.selectByName(roleName);
    }

    public Integer createRole(SysRole sysRole){
        try{
            roleMapper.createRole(sysRole);
        }catch (Exception e){
            return 0;
        }
         return 1;
    }

    public SysRole updateRole(SysRole sysRole){
         roleMapper.updateRole(sysRole);
        return sysRole;
    }

    public SysRole deleteRole(SysRole sysRole){
         roleMapper.deleteRole(sysRole);
        return sysRole;
    }
}
