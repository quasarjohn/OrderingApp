package com.berstek.orderingapp.model;

import android.support.annotation.NonNull;

import java.util.Comparator;

public class Menu implements Comparable<Menu> {

  private String title, details, img_url, code;
  private double price;
  private int priority;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getImg_url() {
    return img_url;
  }

  public void setImg_url(String img_url) {
    this.img_url = img_url;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public Comparator<Menu> getTitleComparator() {
    return titleComparator;
  }

  public void setTitleComparator(Comparator<Menu> titleComparator) {
    this.titleComparator = titleComparator;
  }

  @Override
  public int compareTo(@NonNull Menu menu) {
    return (int) (this.price - menu.getPrice());
  }

  public static Comparator<Menu> titleComparator = new Comparator<Menu>() {
    @Override
    public int compare(Menu menu, Menu t1) {
      return menu.getTitle().compareTo(t1.getTitle());
    }
  };

  public static Comparator<Menu> priorityComparator = new Comparator<Menu>() {
    @Override
    public int compare(Menu menu, Menu t1) {
      return menu.getPriority() - t1.getPriority();
    }
  };

  public static Comparator<Menu> priceComparator = new Comparator<Menu>() {
    @Override
    public int compare(Menu menu, Menu t1) {
      return (int) (menu.getPrice() - t1.getPrice());
    }
  };
}
