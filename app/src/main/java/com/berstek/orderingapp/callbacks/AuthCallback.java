package com.berstek.orderingapp.callbacks;

import com.google.firebase.auth.FirebaseUser;

public interface AuthCallback {

  void onAuthSuccess(FirebaseUser user);
}
