package com.berstek.orderingapp.views.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.data_access.DA;
import com.berstek.orderingapp.views.home.MainActivity;

import java.util.ArrayList;

public class TableSelectionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_table_selection);

    ArrayList<String> tables = new ArrayList<>();
    tables.add("Select Table");
    tables.add("Table 1");
    tables.add("Table 2");
    tables.add("Table 3");
    tables.add("Table 4");
    tables.add("Table 5");


    Spinner spinner = findViewById(R.id.spinner);
    ArrayAdapter<String> adapter = new ArrayAdapter<>(
        this, android.R.layout.simple_spinner_item, tables);
    spinner.setAdapter(adapter);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i != 0) {
          Intent intent = new Intent(TableSelectionActivity.this, MainActivity.class);
          intent.putExtra("table_id", "tbl" + i);
          startActivity(intent);
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
  }
}
