package com.berstek.orderingapp.data_access;

import com.berstek.orderingapp.model.Menu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuDA extends DA {

  private MenuDaCallback menuDaCallback;

  public void queryMenu(final MenuType menuType) {
    rootRef.child(menuType.toString().toLowerCase()).addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Menu menu = dataSnapshot.getValue(Menu.class);
        menu.setKey(dataSnapshot.getKey());
        menuDaCallback.onMenuLoaded(menu);
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Menu menu = dataSnapshot.getValue(Menu.class);
        menu.setKey(dataSnapshot.getKey());
        menuDaCallback.onMenuChanged(menu);
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {
        Menu menu = dataSnapshot.getValue(Menu.class);
        menu.setKey(dataSnapshot.getKey());
        menuDaCallback.onMenuRemoved(menu);
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  ArrayList<Menu> menus = new ArrayList<>();

  public void queryMenu2(final MenuType menuType) {
    rootRef.child(menuType.toString().toLowerCase()).addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        menus.clear();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
          Menu m = child.getValue(Menu.class);
          if (m.isAvailable())
            menus.add(m);
        }
        menuDaCallback.onCompleteMenuLoaded(menus);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }


  public enum MenuType {
    MENUS, DRINKS, DESSERTS;
  }

  public interface MenuDaCallback {
    void onMenuLoaded(Menu menu);

    void onMenuChanged(Menu menu);

    void onMenuRemoved(Menu menu);

    void onCompleteMenuLoaded(ArrayList<Menu> menus);
  }

  public void setMenuDaCallback(MenuDaCallback menuDaCallback) {
    this.menuDaCallback = menuDaCallback;
  }
}
