/**
 * <Write a short description on the purpose of the method>
 *
 * @param <parameter name> <param description>
 * @param <parameter name> <param description>
 * @param <parameter name> <param description>
 *
 * @return <return value description>
 */




package com.ncs.cafe.app.order.model;

public class Order {

    String id;
    String orderNumber;

    public Order(String id, String orderNumber) {
        this.id = id;
        this.orderNumber = orderNumber;
    }

    public Order() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
