package com.berstek.orderingapp.views.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.callbacks.ItemClickCallback;
import com.berstek.orderingapp.callbacks.OnSortTypeChangedListener;
import com.berstek.orderingapp.data_access.MenuDA;
import com.berstek.orderingapp.model.Menu;
import com.berstek.orderingapp.model.MenuFactory;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment implements OnSortTypeChangedListener,
    ItemClickCallback, MenuDA.MenuDaCallback {

  private View view;
  private RecyclerView recviewMenu;
  private ArrayList<Menu> menus;
  private MenuAdapter adapter;
  private ItemClickCallback callback;

  private MenuDA menuDA;

  public MainMenuFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    callback = (ItemClickCallback) context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    view = inflater.inflate(R.layout.fragment_main_menu, container, false);

    menuDA = new MenuDA();
    menuDA.setMenuDaCallback(this);
    menuDA.queryMenu(MenuDA.MenuType.MENUS);

    recviewMenu = view.findViewById(R.id.recview_menu);

    FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
    layoutManager.setFlexDirection(FlexDirection.ROW);
    layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);

    recviewMenu.setLayoutManager(layoutManager);

    menus = new ArrayList<>();
    Collections.sort(menus, Menu.priceComparator);

    adapter = new MenuAdapter(getContext(), menus);


    adapter.setCallback(this);
    recviewMenu.setAdapter(adapter);

    return view;
  }

  @Override
  public void onSort(MainActivity.SortType sortType) {
    if (sortType == MainActivity.SortType.PRICE)
      Collections.sort(menus, Menu.priceComparator);
    else if (sortType == MainActivity.SortType.NAME)
      Collections.sort(menus, Menu.titleComparator);
    else
      Collections.sort(menus, Menu.priorityComparator);

    adapter.notifyDataSetChanged();

  }

  @Override
  public void onItemClick(int p) {
    callback.onMenuSelected(menus.get(p), 0);
  }

  @Override
  public void onMenuSelected(Menu menu) {

  }

  @Override
  public void onMenuSelected(Menu menu, int source) {

  }

  @Override
  public void onMenuLoaded(Menu menu) {
    menus.add(menu);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onMenuChanged(Menu menu) {
    for (int i = 0; i < menus.size(); i++) {
      if (menus.get(i).getKey().equals(menu.getKey())) {
        menus.set(i, menu);
        adapter.notifyItemChanged(i);
        break;
      }
    }
  }

  @Override
  public void onMenuRemoved(Menu menu) {
    for (int i = 0; i < menus.size(); i++) {
      if (menus.get(i).getKey().equals(menu.getKey())) {
        menus.remove(i);
        adapter.notifyItemRemoved(i);
        break;
      }
    }
  }
}
