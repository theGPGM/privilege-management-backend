package org.george.pm.model;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;

    private String name;

    private String nameZh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String namezh) {
        this.nameZh = namezh == null ? null : namezh.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", namezh='" + nameZh + '\'' +
                '}';
    }
}