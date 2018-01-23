package com.berstek.orderingapp.views.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.berstek.orderingapp.R;
import com.berstek.orderingapp.model.Menu;
import com.berstek.orderingapp.utils.StringUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProceedAdapter extends RecyclerView.Adapter<ProceedAdapter.ListHolder> {

  private Context context;
  private ArrayList<Menu> menus;
  private LayoutInflater inflater;

  private OnItemRemovedListener onItemRemovedListener;

  public void setOnItemRemovedListener(OnItemRemovedListener onItemRemovedListener) {
    this.onItemRemovedListener = onItemRemovedListener;
  }

  public ProceedAdapter(Context context, ArrayList<Menu> menus) {
    this.context = context;
    this.menus = menus;
    inflater = LayoutInflater.from(context);
  }

  @Override
  public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.viewholder_proceed, parent, false);

    return new ListHolder(view);
  }

  @Override
  public void onBindViewHolder(ListHolder holder, int position) {
    Menu menu = menus.get(position);
    holder.subtitle.setText(StringUtils.formatWithPesoSign(menu.getPrice()));
    holder.title.setText(menu.getTitle());

    Glide.with(context).
        load(menu.getImg_url()).skipMemoryCache(true).
        override(100, 100).into(holder.foodImg);
  }

  @Override
  public int getItemCount() {
    return menus.size();
  }

  class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title, subtitle;
    private ImageView foodImg;

    public ListHolder(View itemView) {
      super(itemView);

      foodImg = itemView.findViewById(R.id.food_img);
      title = itemView.findViewById(R.id.title);
      subtitle = itemView.findViewById(R.id.subtitle);

      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      onItemRemovedListener.onItemRemoved(getAdapterPosition());
    }
  }

  public interface OnItemRemovedListener {
    void onItemRemoved(int index);
  }
}
