package com.example.indi_pro3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener {
    Context context;
    private ArrayList<ItemMed> arrayList = new ArrayList<>();

    HomeAdapter(Context context, boolean z) {
        this.context = context;
        TAGS.isFromOrders = z;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reycler_search_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.ivsearchimage.setImageResource(arrayList.get(position).getImageId());
        holder.tvmedicinename.setText(arrayList.get(position).getName());
        holder.tvmedicinenamepower.setText(arrayList.get(position).getDescription1());
        holder.tvmedicinenamepower1.setText(arrayList.get(position).getDescription2());
        holder.tvmedicineqty.setText("Pack Size: " + arrayList.get(position).getSize());
        holder.mrp.setText(TAGS.getMultiplyPrice(arrayList.get(position).getPrice(), arrayList.get(position).getQty()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAGS.MEDS = arrayList.get(position);
                context.startActivity(new Intent(context, DetailsActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void updateArrayList(ArrayList<ItemMed> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
       /* switch (v.getId()) {
            case R.id.ivminus:
                break;
            case R.id.ivplus:
                break;
        }*/

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivsearchimage, ivminus, ivplus;
        TextView tvno, tvmedicinename, tvmedicinenamepower, tvmedicinenamepower1, tvmedicineqty, mrp;
        RelativeLayout add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivsearchimage = itemView.findViewById(R.id.ivsearchimage);
            /*ivminus = itemView.findViewById(R.id.ivminus);
            ivplus = itemView.findViewById(R.id.ivplus);*/
            //tvno = itemView.findViewById(R.id.tvno);
            add = itemView.findViewById(R.id.add);
            tvmedicinename = itemView.findViewById(R.id.tvmedicinename);
            tvmedicinenamepower = itemView.findViewById(R.id.tvmedicinenamepower);
            tvmedicinenamepower1 = itemView.findViewById(R.id.tvmedicinenamepower1);
            tvmedicineqty = itemView.findViewById(R.id.tvmedicineqty);
            mrp = itemView.findViewById(R.id.mrp);
        }
    }
}
