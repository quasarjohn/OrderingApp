package com.berstek.orderingapp.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class Order {

  private ArrayList<Menu> cart;
  private long time_stamp;
  private String table_id;
  private OrderStatus order_status;

  @Exclude
  private String key;

  public enum OrderStatus {
    QUEUED, READY, SERVED
  }

  public ArrayList<Menu> getCart() {
    return cart;
  }

  public void setCart(ArrayList<Menu> cart) {
    this.cart = cart;
  }

  public long getTime_stamp() {
    return time_stamp;
  }

  public void setTime_stamp(long time_stamp) {
    this.time_stamp = time_stamp;
  }

  public String getTable_id() {
    return table_id;
  }

  public void setTable_id(String table_id) {
    this.table_id = table_id;
  }

  public OrderStatus getOrder_status() {
    return order_status;
  }

  public void setOrder_status(OrderStatus order_status) {
    this.order_status = order_status;
  }

  @Exclude
  public String getKey() {
    return key;
  }

  @Exclude
  public void setKey(String key) {
    this.key = key;
  }
}
