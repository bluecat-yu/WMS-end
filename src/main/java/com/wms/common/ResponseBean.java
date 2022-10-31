package com.wms.common;

import lombok.Data;

@Data
public class ResponseBean {
    private Integer code;
 
    private String msg;
 
    private Object data;
 
    public static ResponseBean build() {
        return new ResponseBean();
    }
 
    public static ResponseBean success(String msg) {
        return new ResponseBean(200, msg, null);
    }
 
    public static ResponseBean success(String msg, Object obj) {
        return new ResponseBean(200, msg, obj);
    }
 
    public static ResponseBean error(String msg) {
        return new ResponseBean(500, msg, null);
    }
 
    public static ResponseBean error(String msg, Object obj) {
        return new ResponseBean(500, msg, obj);
    }
 
    private ResponseBean() {
 
    }
 
    private ResponseBean(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.data = obj;
    }
}