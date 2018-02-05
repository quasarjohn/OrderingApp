package com.berstek.orderingapp.views.home;

import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.callbacks.BinaryDecisionCallback;
import com.berstek.orderingapp.callbacks.ItemClickCallback;
import com.berstek.orderingapp.callbacks.OnSortTypeChangedListener;
import com.berstek.orderingapp.data_access.DA;
import com.berstek.orderingapp.data_access.OrderDA;
import com.berstek.orderingapp.model.Menu;
import com.berstek.orderingapp.model.Order;
import com.berstek.orderingapp.utils.StringUtils;


import java.util.ArrayList;
import java.util.List;

import static com.berstek.orderingapp.views.home.MainActivity.SortType.NAME;
import static com.berstek.orderingapp.views.home.MainActivity.SortType.PRICE;
import static com.berstek.orderingapp.views.home.MainActivity.SortType.PRIORITY;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
    ItemClickCallback, MenuDialogFragment.MenuDialogFragmentCallback, CartAdapter.OnItemRemovedListener,
    View.OnClickListener {

  private TabLayout tabLayout;
  private ViewPager viewPager;
  private ArrayList<Fragment> fragments = new ArrayList<>();
  private RadioGroup radioGroup;
  private SortType sortType = PRIORITY;
  private ArrayList<Menu> cart;
  private RecyclerView recview_cart;
  private CartAdapter cartAdapter;
  private TextView cartPrice;

  private Button proceedBtn;
  private ProceedDialogFragment dialogFragment;
  private WaitingDialogFragment waitingDialogFragment;

  private OrderDA orderDA;

  private String orderKey;
  private String table_id;

  @Override
  public void onItemClick(int p) {

  }

  @Override
  public void onMenuSelected(Menu menu) {

  }

  @Override
  public void onMenuSelected(Menu menu, int source) {
    MenuDialogFragment dialogFragment = new MenuDialogFragment();

    ArrayList menus = new ArrayList();
    menus.add(menu);

    Bundle args = new Bundle();
    args.putParcelableArrayList("menu", menus);
    dialogFragment.setArguments(args);

    dialogFragment.setCallback(this);

    dialogFragment.show(getFragmentManager(), null);

    if (source != 0) {
      dialogFragment.hideDrinks();
    }
  }

  @Override
  public void onAddToCart(ArrayList<Menu> menus, int quantity) {
    for (int i = 0; i < quantity; i++) {
      for (Menu menu : menus) {
        cart.add(menu);
        recview_cart.smoothScrollToPosition(cart.size() - 1);
        cartAdapter.notifyItemInserted(cart.size() - 1);
      }
    }
    computeTotalCartPrice();
  }

  @Override
  public void onClick(View view) {
    dialogFragment = new ProceedDialogFragment();
    dialogFragment.setBinaryDecisionCallback(new BinaryDecisionCallback() {
      @Override
      public void onCancel() {
        dialogFragment.dismiss();
      }

      @Override
      public void onProceed() {
        Order order = new Order();
        order.setTable_id(table_id);
        order.setCart(cart);
        order.setTime_stamp(System.currentTimeMillis());

        orderKey = orderDA.pushOrderToQueue(order);
        new DA().log(orderKey);

        cart.clear();
        cartAdapter.notifyDataSetChanged();
        dialogFragment.dismiss();

        Bundle bundle = new Bundle();
        bundle.putString("orderKey", orderKey);

        waitingDialogFragment = new WaitingDialogFragment();
        waitingDialogFragment.setArguments(bundle);
        waitingDialogFragment.show(getFragmentManager(), null);
      }
    });

    ArrayList carts = new ArrayList<>();
    carts.add(cart);

    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList("carts", carts);
    dialogFragment.setArguments(bundle);

    dialogFragment.show(getFragmentManager(), null);
  }


  public enum SortType {
    PRIORITY, PRICE, NAME
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    table_id = getIntent().getExtras().getString("table_id");

    orderDA = new OrderDA();

    getSupportActionBar().hide();
    cart = new ArrayList<>();

    recview_cart = findViewById(R.id.recview_cart);
    cartPrice = findViewById(R.id.cart_price);
    proceedBtn = findViewById(R.id.proceed_btn);

    proceedBtn.setOnClickListener(this);

    radioGroup = findViewById(R.id.radiogroup);
    radioGroup.setOnCheckedChangeListener(this);

    //setup tabs
    tabLayout = findViewById(R.id.tab);
    viewPager = findViewById(R.id.viewpager);

    Fragment mainMenuFragment = new MainMenuFragment();
    fragments.add(mainMenuFragment);

    Fragment drinksFragment = new MainMenuFragment();
    fragments.add(drinksFragment);

    Fragment dessertsFragment = new MainMenuFragment();
    fragments.add(dessertsFragment);

    for (int i = 0; i < fragments.size(); i++) {
      tabLayout.addTab(tabLayout.newTab());
    }

    viewPager.setAdapter(new ViewpagerAdapter(getSupportFragmentManager()));
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.setupWithViewPager(viewPager);

    tabLayout.getTabAt(0).setText("MAIN MENU");
    tabLayout.getTabAt(1).setText("DRINKS");
    tabLayout.getTabAt(2).setText("DESSERTS");

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    initRecviewCart();
  }

  @Override
  public void onCheckedChanged(RadioGroup radioGroup, int id) {
    if (id == R.id.priority) {
      sortType = PRIORITY;
    } else if (id == R.id.name) {
      sortType = NAME;
    } else if (id == R.id.price) {
      sortType = PRICE;
    }

    OnSortTypeChangedListener fragment = (OnSortTypeChangedListener) fragments.get
        (tabLayout.getSelectedTabPosition());
    fragment.onSort(sortType);

  }

  class ViewpagerAdapter extends FragmentStatePagerAdapter {

    public ViewpagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return fragments.get(position);
    }

    @Override
    public int getCount() {
      return fragments.size();
    }
  }

  private void initRecviewCart() {
    recview_cart = findViewById(R.id.recview_cart);
    recview_cart.setLayoutManager(new LinearLayoutManager(this));

    cartAdapter = new CartAdapter(this, cart);
    cartAdapter.setOnItemRemovedListener(this);
    recview_cart.setAdapter(cartAdapter);
  }


  @Override
  public void onItemRemoved(int index) {
    cart.remove(index);
    cartAdapter.notifyItemRemoved(index);
    computeTotalCartPrice();
  }

  private void computeTotalCartPrice() {
    double price = 0;
    for (Menu menu : cart) {
      price += menu.getPrice();
    }
    cartPrice.setText(StringUtils.formatWithPesoSign(price));
  }
}
