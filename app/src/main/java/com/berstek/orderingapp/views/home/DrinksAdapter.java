package com.berstek.orderingapp.views.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.model.Menu;

import java.util.ArrayList;

public class DrinksAdapter extends MenuAdapter {
  public DrinksAdapter(Context context, ArrayList<Menu> data) {
    super(context, data);
  }

  @Override
  public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = inflater.inflate(R.layout.viewholder_drinks, parent, false);
    return new ListHolder(v);
  }

}
