package com.berstek.orderingapp.data_access;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DA {

  public DatabaseReference rootRef;

  public DA() {
    rootRef = FirebaseDatabase.getInstance().getReference().getRoot();
  }

  public void log(Object o) {
    rootRef.child("logs").push().setValue(o);
  }
}
