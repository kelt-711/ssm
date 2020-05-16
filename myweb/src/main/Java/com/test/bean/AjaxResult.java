package com.test.service.bean;

public class AjaxResult {

    //用于后台的逻辑判断
    private boolean success;
    //后台向前端返回的对象
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
