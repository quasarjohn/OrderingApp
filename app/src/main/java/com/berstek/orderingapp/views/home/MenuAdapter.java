package com.berstek.orderingapp.views.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ListHolder> {

  LayoutInflater inflater;
  Context context;
  ArrayList<Menu> data;
  ItemClickCallback callback;

  public void setCallback(ItemClickCallback callback) {
    this.callback = callback;
  }

  public MenuAdapter(Context context, ArrayList<Menu> data) {
    this.context = context;
    this.data = data;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = inflater.inflate(R.layout.viewholder_menu, parent, false);
    return new ListHolder(v);
  }

  @Override
  public void onBindViewHolder(ListHolder holder, int position) {
    Menu m = data.get(position);
    holder.subtitle.setText(StringUtils.removeDecimalIfZero(
        StringUtils.getPesoSign() + " " + StringUtils.formatDf00(m.getPrice())));
    holder.title.setText(m.getTitle());
    holder.details.setText(m.getDetails());

    Log.d(null, m.getImg_url());

    Glide.with(context).load(m.getImg_url()).
        skipMemoryCache(true).
        override(300, 200).
        centerCrop().
        into(holder.foodImg);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title, subtitle, details;
    private ImageView foodImg;

    public ListHolder(View itemView) {
      super(itemView);

      title = itemView.findViewById(R.id.title);
      subtitle = itemView.findViewById(R.id.subtitle);
      details = itemView.findViewById(R.id.details);
      foodImg = itemView.findViewById(R.id.food_image);


      ViewGroup.LayoutParams lp = itemView.getLayoutParams();
      if (lp instanceof FlexboxLayoutManager.LayoutParams) {
        FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
        flexboxLp.setFlexGrow(1.0f);
        flexboxLp.setAlignSelf(AlignSelf.FLEX_END);

      }

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      callback.onItemClick(getAdapterPosition());
    }
  }
}
