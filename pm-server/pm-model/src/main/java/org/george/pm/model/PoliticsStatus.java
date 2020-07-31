package org.george.pm.model;

import java.io.Serializable;
import java.util.Objects;

public class PoliticsStatus implements Serializable {
    private Integer id;

    private String name;

    public PoliticsStatus(String name) {
        this.name = name;
    }

    public PoliticsStatus() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticsStatus that = (PoliticsStatus) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

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
}