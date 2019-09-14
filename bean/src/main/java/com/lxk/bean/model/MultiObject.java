package com.lxk.bean.model;

/**
 * 泛型类的使用
 *
 * @param <T> 包含对象的类型
 */
public class MultiObject<T> {

    /**
     * 成功状态
     */
    private boolean success;

    /**
     * 异常
     */
    private Exception ex;

    /**
     * 数据
     */
    private T obj;

    public MultiObject() {
    }

    /**
     * 注意：当传入的泛型是Boolean时，就和第三个构造函数冲突了。
     */
    public MultiObject(boolean success) {
        this.success = success;
    }

    public MultiObject(Exception ex) {
        this.success = false;
        this.ex = ex;
    }

    public MultiObject(T value) {
        this.success = true;
        this.obj = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
