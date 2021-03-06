package com.tmarat.theweather.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tmarat.theweather.R;
import com.tmarat.theweather.data.Data;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
  private ArrayList<Data> dataList;
  private OnItemClickListener onItemClickListener;

  public RecyclerViewAdapter(ArrayList<Data> dataList) {
    this.dataList = dataList;
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  @NonNull
  @Override
  public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.textViewNameCity.setText(dataList.get(position).getCityName());
    holder.textViewTem.setText(dataList.get(position).getTemperature());
  }

  @Override
  public int getItemCount() {
    return dataList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewNameCity;
    private TextView textViewTem;

    ViewHolder(View itemView) {
      super(itemView);
      textViewNameCity = itemView.findViewById(R.id.item_city_name);
      textViewTem = itemView.findViewById(R.id.item_city_tem);

      textViewNameCity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (onItemClickListener != null) {
            onItemClickListener.onItemClick(dataList.get(getAdapterPosition()));
          }
        }
      });
    }
  }

  public interface OnItemClickListener {
    void onItemClick(Data data);
  }
}
