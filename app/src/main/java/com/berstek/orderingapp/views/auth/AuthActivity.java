package com.berstek.orderingapp.views.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.callbacks.AuthCallback;
import com.berstek.orderingapp.data_access.DA;
import com.berstek.orderingapp.presentor.auth.GoogleAuthP;
import com.berstek.orderingapp.utils.RequestCodes;
import com.berstek.orderingapp.views.home.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements AuthCallback {

  private Button signinBtn;

  private GoogleAuthP googleAuthP;
  private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
  ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth);

    googleAuthP = new GoogleAuthP(this, firebaseAuth);
    googleAuthP.setGoogleAuthCallback(this);

    getSupportActionBar().hide();
    signinBtn = findViewById(R.id.signin_btn);

    signinBtn.setOnClickListener(googleAuthP);
  }

  @Override
  protected void onStart() {
    super.onStart();

    if (firebaseAuth.getCurrentUser() != null) {
      openMainActivity();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RequestCodes.SIGN_IN) {
      googleAuthP.loginToFirebase(data);
    }
  }

  @Override
  public void onAuthSuccess(FirebaseUser user) {
    openMainActivity();
  }

  private void openMainActivity() {
    Intent intent = new Intent(AuthActivity.this, TableSelectionActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
