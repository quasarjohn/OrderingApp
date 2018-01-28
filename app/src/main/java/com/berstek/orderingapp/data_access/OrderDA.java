package com.berstek.orderingapp.data_access;

import com.berstek.orderingapp.model.Order;
import com.berstek.orderingapp.utils.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class OrderDA extends DA {

  public final String node = "orders_queue";
  private final String completed_node = "orders_completed";

  public String pushOrderToQueue(Order order) {
    DatabaseReference ref = rootRef.child(node).push();
    ref.setValue(order);
    return ref.getKey();
  }

  public void removeOrderFromQueue(String orderKey) {
    rootRef.child(node).child(orderKey).setValue(null);
  }

  public void moveOrderToCompleted(Order order) {
    String key = order.getKey();
    order.setKey(null);
    rootRef.child(completed_node).
        child(Utils.getCurrentEpochDate() + "").
        child(key).setValue(order);
  }

  public void listenToOrderCompletion() {
    rootRef.child(node).addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        orderStatusListener.onOrderAdded(dataSnapshot);
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {
        orderStatusListener.onOrderRemoved(dataSnapshot);
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  public interface OrderStatusListener {
    void onOrderRemoved(DataSnapshot dataSnapshot);

    void onOrderAdded(DataSnapshot dataSnapshot);
  }

  private OrderStatusListener orderStatusListener;

  public void setOrderStatusListener(OrderStatusListener orderStatusListener) {
    this.orderStatusListener = orderStatusListener;
  }
}
