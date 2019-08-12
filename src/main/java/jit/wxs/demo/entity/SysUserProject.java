package jit.wxs.demo.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysUserProject implements Serializable {

    static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer projectId;
}
