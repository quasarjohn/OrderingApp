package com.berstek.orderingapp.views.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.callbacks.ItemClickCallback;
import com.berstek.orderingapp.model.Menu;
import com.berstek.orderingapp.utils.StringUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DialogDrinksAdapter extends RecyclerView.Adapter<DialogDrinksAdapter.ListHolder> {

  private Context context;
  private ArrayList<Menu> drinks;
  private LayoutInflater layoutInflater;
  private ItemClickCallback callback;

  public void setCallback(ItemClickCallback callback) {
    this.callback = callback;
  }

  public DialogDrinksAdapter(Context context, ArrayList<Menu> drinks) {
    this.context = context;
    this.drinks = drinks;

    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = layoutInflater.inflate(R.layout.viewholder_drink_dialog, parent, false);
    return new ListHolder(v);
  }

  @Override
  public void onBindViewHolder(ListHolder holder, int position) {
    Menu drink = drinks.get(position);

    holder.priceTxt.setText(StringUtils.formatWithPesoSign(drink.getPrice()));

    Glide.with(context).
        load(drink.getImg_url()).
        override(200, 200).
        skipMemoryCache(true).into(holder.img);
  }

  @Override
  public int getItemCount() {
    return drinks.size();
  }

  class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView img;
    private TextView priceTxt;

    public ListHolder(View itemView) {
      super(itemView);
      img = itemView.findViewById(R.id.food_img);
      priceTxt = itemView.findViewById(R.id.priceTxt);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      callback.onItemClick(getAdapterPosition());
    }
  }
}
