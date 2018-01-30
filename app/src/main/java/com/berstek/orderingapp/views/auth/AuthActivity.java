package com.berstek.orderingapp.views.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.views.home.MainActivity;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

  private Button signinBtn;
  private Spinner spinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth);

    getSupportActionBar().hide();
    signinBtn = findViewById(R.id.signin_btn);
    spinner = findViewById(R.id.spinner);

    signinBtn.setOnClickListener(this);

  }

  @Override
  public void onClick(View view) {
    Intent intent = new Intent(this, TableSelectionActivity.class);
    startActivity(intent);
  }
}
