package org.george.pm.model;

import org.omg.PortableServer.POAPackage.ObjectAlreadyActive;

import java.util.List;

public class RespPageBean {
    /**
     * 总记录数
     */
    private Long total;

    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
