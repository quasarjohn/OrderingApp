package com.berstek.orderingapp.model;

import java.util.ArrayList;

public class OrderItem {

  private ArrayList<Menu> menus;
  private int quantity;

  public ArrayList<Menu> getMenus() {
    return menus;
  }

  public void setMenus(ArrayList<Menu> menus) {
    this.menus = menus;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
