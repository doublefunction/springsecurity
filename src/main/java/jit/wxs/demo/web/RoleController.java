package jit.wxs.demo.web;

import jit.wxs.demo.entity.SysRole;
import jit.wxs.demo.service.SysRoleService;
import jit.wxs.demo.util.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 创建角色
     * @param roleInfo
     * @return
     */
    @RequestMapping("/create")
    public CommonReturnType create(SysRole roleInfo) {

        Integer result = roleService.createRole(roleInfo);

        if(result==1){
            return CommonReturnType.create("插入成功");
        }else{
            return CommonReturnType.create("插入失败");
        }
    }

//    /**
//     * 修改角色信息
//     * @param roleInfo
//     * @return
//     */
//    @PutMapping("/{id}")
//    public RoleInfo update(@RequestBody RoleInfo roleInfo) {
//        return roleService.update(roleInfo);
//    }
//
//    /**
//     * 删除角色
//     * @param id
//     */
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        roleService.delete(id);
//    }
//
//    /**
//     * 获取角色详情
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public RoleInfo getInfo(@PathVariable Long id) {
//        return roleService.getInfo(id);
//    }
//
//    /**
//     * 获取所有角色
//     * @param roleInfo
//     * @param pageable
//     * @return
//     */
//    @GetMapping
//    public List<RoleInfo> findAll() {
//        return roleService.findAll();
//    }
//
//    /**
//     * 获取角色的所有资源
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}/resource")
//    public String[] getRoleResources(@PathVariable Long id){
//        return roleService.getRoleResources(id);
//    }
//
//    /**
//     * 创建用户的资源
//     * @param id
//     * @param ids
//     */
//    @PostMapping("/{id}/resource")
//    public void createRoleResource(@PathVariable Long id, String ids){
//        roleService.setRoleResources(id, ids);
//    }

}
