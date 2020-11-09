package com.lxb.entity;

import java.io.Serializable;

public class OaAuthority implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private Integer authority_module_id;

    private String authority_url;

    private Boolean type;

    private Integer sequence;

    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAuthority_module_id() {
        return authority_module_id;
    }

    public void setAuthority_module_id(Integer authority_module_id) {
        this.authority_module_id = authority_module_id;
    }

    public String getAuthority_url() {
        return authority_url;
    }

    public void setAuthority_url(String authority_url) {
        this.authority_url = authority_url == null ? null : authority_url.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", authority_module_id=").append(authority_module_id);
        sb.append(", authority_url=").append(authority_url);
        sb.append(", type=").append(type);
        sb.append(", sequence=").append(sequence);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}