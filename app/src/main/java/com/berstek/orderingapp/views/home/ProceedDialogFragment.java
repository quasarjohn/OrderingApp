package com.berstek.orderingapp.views.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.callbacks.BinaryDecisionCallback;
import com.berstek.orderingapp.custom_classes.CustomDialogFragment;
import com.berstek.orderingapp.model.Menu;
import com.berstek.orderingapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProceedDialogFragment extends CustomDialogFragment implements View.OnClickListener {

  private RecyclerView recyclerView;
  private List carts;
  private ArrayList<Menu> cart;
  private TextView total;

  private Button proceed, back;

  private BinaryDecisionCallback binaryDecisionCallback;

  public ProceedDialogFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fragment_proceed_dialog, container, false);
    recyclerView = view.findViewById(R.id.recview);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    total = view.findViewById(R.id.total);

    proceed = view.findViewById(R.id.proceed);
    back = view.findViewById(R.id.back);

    proceed.setOnClickListener(this);
    back.setOnClickListener(this);

    carts = getArguments().getParcelableArrayList("carts");
    cart = (ArrayList<Menu>) carts.get(0);

    double price = 0;
    for (Menu m : cart) {
      price += m.getPrice();
    }

    total.setText("Total: " + StringUtils.formatWithPesoSign(price));

    ProceedAdapter adapter = new ProceedAdapter(getActivity(), cart);
    recyclerView.setAdapter(adapter);

    super.onCreateView(inflater, container, savedInstanceState);
    return view;
  }

  @Override
  public void onClick(View view) {
    int id = view.getId();

    if (id == R.id.proceed) {
      binaryDecisionCallback.onProceed();
    } else {
      binaryDecisionCallback.onCancel();
    }
  }

  public void setBinaryDecisionCallback(BinaryDecisionCallback binaryDecisionCallback) {
    this.binaryDecisionCallback = binaryDecisionCallback;
  }
}
