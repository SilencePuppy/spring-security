package com.lxb.entity;

import java.io.Serializable;

public class OaRoleAuthorityMap implements Serializable {
    private Integer id;

    private Integer role_id;

    private Integer authority_module_id;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getAuthority_module_id() {
        return authority_module_id;
    }

    public void setAuthority_module_id(Integer authority_module_id) {
        this.authority_module_id = authority_module_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", role_id=").append(role_id);
        sb.append(", authority_module_id=").append(authority_module_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}