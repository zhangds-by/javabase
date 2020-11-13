package com.zhangds.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserVo extends User implements Serializable {

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList==null ? roleList : new ArrayList<>();
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
