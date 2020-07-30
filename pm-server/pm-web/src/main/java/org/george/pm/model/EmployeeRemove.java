package org.george.pm.model;

import java.util.Date;

public class EmployeeRemove {
    private Integer id;

    private Integer eid;

    private Integer afterDepid;

    private Integer afterJobid;

    private Date removeDate;

    private String reason;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getAfterDepid() {
        return afterDepid;
    }

    public void setAfterDepid(Integer afterDepid) {
        this.afterDepid = afterDepid;
    }

    public Integer getAfterJobid() {
        return afterJobid;
    }

    public void setAfterJobid(Integer afterJobid) {
        this.afterJobid = afterJobid;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}