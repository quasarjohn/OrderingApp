package com.berstek.orderingapp.views.home;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.custom_classes.CustomDialogFragment;
import com.berstek.orderingapp.data_access.DA;
import com.berstek.orderingapp.data_access.OrderDA;
import com.berstek.orderingapp.model.Order;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingDialogFragment extends CustomDialogFragment {

  private TextView statusTxt, descTxt;
  private String orderKey;
  private OrderDA orderDA;
  private ArrayList<Order> orders;
  private ImageView statusImg;
  private int orderPosition;

  public WaitingDialogFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_waiting_dialog, container, false);
    statusTxt = view.findViewById(R.id.statusTxt);
    statusImg = view.findViewById(R.id.statusImg);
    descTxt = view.findViewById(R.id.descTxt);

    orderKey = getArguments().getString("orderKey");
    orderDA = new OrderDA();
    orders = new ArrayList<>();

    orderDA.setOrderStatusListener(new OrderDA.OrderStatusListener() {
      @Override
      public void onOrderRemoved(DataSnapshot d) {
        if (d.getKey().equals(orderKey)) {
          statusTxt.setText("Order Ready");
          descTxt.setText("Your order is now ready. We'll serve it to you in a minute.");
          statusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_ready));
        }
      }

      @Override
      public void onOrderAdded(DataSnapshot d) {
        orders.add(d.getValue(Order.class));
      }
    });

    orderDA.listenToOrderCompletion();

    super.onCreateView(inflater, container, savedInstanceState);
    return view;
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
  }
}
