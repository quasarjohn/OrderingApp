package com.berstek.orderingapp.views.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.callbacks.ItemClickCallback;
import com.berstek.orderingapp.custom_classes.CustomDialogFragment;
import com.berstek.orderingapp.model.Menu;
import com.berstek.orderingapp.model.MenuFactory;
import com.berstek.orderingapp.utils.CustomImageUtils;
import com.berstek.orderingapp.utils.StringUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDialogFragment extends CustomDialogFragment implements View.OnClickListener, ItemClickCallback {

  private View view;
  private TextView title, subtitle, total, quantity, title2, subtitle2, drinkTitle;
  private ImageView foodImg, minus, plus;
  private Button addToCart;

  private RecyclerView recview;
  private DialogDrinksAdapter adapter;

  private ArrayList<Menu> drinks;
  private int q = 1;
  private Menu drink = null;
  private Menu menu;

  boolean hideDrinks = false;

  private MenuDialogFragmentCallback callback;

  public void setCallback(MenuDialogFragmentCallback callback) {
    this.callback = callback;
  }

  public MenuDialogFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_menu_dialog, container, false);

    ArrayList menus = getArguments().getParcelableArrayList("menu");
    menu = (Menu) menus.get(0);

    recview = view.findViewById(R.id.recview);

    title = view.findViewById(R.id.title);
    subtitle = view.findViewById(R.id.subtitle);
    total = view.findViewById(R.id.total);
    foodImg = view.findViewById(R.id.food_img);
    minus = view.findViewById(R.id.minus);
    plus = view.findViewById(R.id.plus);
    quantity = view.findViewById(R.id.quantityTxt);
    addToCart = view.findViewById(R.id.add_to_cart_btn);
    title2 = view.findViewById(R.id.title2);
    subtitle2 = view.findViewById(R.id.subtitle2);
    drinkTitle = view.findViewById(R.id.drinkTitle);


    addToCart.setOnClickListener(this);

    String p = StringUtils.formatWithPesoSign(menu.getPrice());

    title.setText(menu.getTitle());
    subtitle.setText(p);
    total.setText("Total: " + p);

    minus.setOnClickListener(this);
    plus.setOnClickListener(this);

    CustomImageUtils customImageUtils = new CustomImageUtils(600, 300);
    customImageUtils.blurImage(getActivity(), menu.getImg_url(), foodImg, false);

    initDrinksRecview();



    super.onCreateView(inflater, container, savedInstanceState);
    return view;
  }

  private void initDrinksRecview() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
    recview.setLayoutManager(linearLayoutManager);

    drinks = new MenuFactory().getDrinks();

    adapter = new DialogDrinksAdapter(getActivity(), drinks);
    adapter.setCallback(this);
    recview.setAdapter(adapter);

    if (hideDrinks) {
      drinkTitle.setVisibility(View.GONE);
      recview.setVisibility(View.GONE);
    }
  }


  @SuppressLint("SetTextI18n")
  @Override
  public void onClick(View view) {
    int id = view.getId();

    if (id == R.id.add_to_cart_btn) {
      ArrayList<Menu> menus = new ArrayList<>();
      menus.add(menu);
      if (drink != null)
        menus.add(drink);
      callback.onAddToCart(menus, q);
      dismiss();
    } else {
      if (id == R.id.minus && q != 1) {
        q--;
      } else if (id == R.id.plus) {
        q++;
      }
      quantity.setText(q + "");
      total.setText("Total: " + StringUtils.formatWithPesoSign(computeTotalPrice()));
    }
  }


  @Override
  public void onItemClick(int p) {
    drink = drinks.get(p);
    title2.setText(drink.getTitle());
    subtitle2.setText(StringUtils.formatWithPesoSign(drink.getPrice()));
    total.setText("Total: " + StringUtils.formatWithPesoSign(computeTotalPrice()));
  }

  @Override
  public void onMenuSelected(Menu menu) {

  }

  @Override
  public void onMenuSelected(Menu menu, int source) {

  }

  private double computeTotalPrice() {
    double p1 = menu.getPrice() * q;
    double p2 = drink != null ? drink.getPrice() * q : 0;
    return p1 + p2;
  }

  public interface MenuDialogFragmentCallback {
    void onAddToCart(ArrayList<Menu> menus, int quantity);
  }

  public void hideDrinks() {
    hideDrinks = true;
  }
}
