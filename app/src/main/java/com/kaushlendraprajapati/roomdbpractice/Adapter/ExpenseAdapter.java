package com.kaushlendraprajapati.roomdbpractice.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaushlendraprajapati.roomdbpractice.Expense;
import com.kaushlendraprajapati.roomdbpractice.R;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.Viewholder> {
    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rawlayout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.Viewholder holder, int position) {
        holder.tvTitle.setText("Title -: "+expenseList.get(position).getTitle());
        holder.tvAmount.setText("Amount - ₹"+expenseList.get(position).getAmount());
        holder.tvID.setText("Sr.no - "+expenseList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvAmount,tvID;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvAmount=itemView.findViewById(R.id.tv_amount);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvID=itemView.findViewById(R.id.tv_id);
        }
    }
}
