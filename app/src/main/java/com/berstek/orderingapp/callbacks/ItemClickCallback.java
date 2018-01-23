package com.berstek.orderingapp.callbacks;

import com.berstek.orderingapp.model.Menu;

public interface ItemClickCallback {

  void onItemClick(int p);

  void onMenuSelected(Menu menu);
  void onMenuSelected(Menu menu, int source);
}
