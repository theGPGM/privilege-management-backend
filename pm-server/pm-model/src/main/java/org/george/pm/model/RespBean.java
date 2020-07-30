package org.george.pm.model;

public class RespBean {

    private Integer status;
    private String message;
    private Object obj;

    public static RespBean ok(String message){
        return new RespBean(200, message, null);
    }

    public static RespBean ok(String message, Object obj){
        return new RespBean(200, message, obj);
    }

    public static RespBean error(String message){
        return new RespBean(500, message,null);
    }

    public static RespBean error(String message, Object obj){
        return new RespBean(500, message,obj);
    }

    public static RespBean build(){
        return new RespBean();
    }

    private RespBean() {
    }

    private RespBean(Integer status, String message, Object obj) {
        this.status = status;
        this.message = message;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RespBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
