package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;


public abstract class BaseDomain {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
