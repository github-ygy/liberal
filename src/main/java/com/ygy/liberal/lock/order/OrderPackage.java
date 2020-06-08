package com.ygy.liberal.lock.order;

/**
 * @author guoyao
 * @date 2020-04-30
 */
public class OrderPackage<T> {
    private Integer batch;

    private Integer order;

    private OrderStatus status;

    private T t;

    public OrderPackage(int batch, int order, OrderStatus status) {
        this.batch = batch;
        this.order = order;
        this.status = status;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
