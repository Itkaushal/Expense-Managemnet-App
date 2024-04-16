package com.kaushlendraprajapati.roomdbpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kaushlendraprajapati.roomdbpractice.Adapter.ExpenseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText title,amount,id;
    Button submit,delete,update;
    static DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ExpenseAdapter adapter;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=findViewById(R.id.et_title);
        amount=findViewById(R.id.et_amount);
        submit=findViewById(R.id.button_submit);
        id=findViewById(R.id.et_id);
        delete=findViewById(R.id.delete_button);
        update=findViewById(R.id.update_button);
        recyclerView=findViewById(R.id.recyView);
        refreshLayout=findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        databaseHelper= Room.databaseBuilder(getApplicationContext(),DatabaseHelper.class,"expenseDB").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();

        // save expense
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExpense();
            }
        });

        // update expense
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExpense();
            }
        });

        // delete expense
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteExpense();
            }
        });


    }

    // refresh content methode
    private void refreshContent() {
        getExpense();

        refreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    private void deleteExpense() {

        int eid = 0;
        try {
            eid=Integer.parseInt(id.getText().toString());
        } catch (NumberFormatException exception){
            Toast.makeText(this, "invalid argument!", Toast.LENGTH_SHORT).show();
            return;
        }
        Expense expense = new Expense();
        expense.setId(eid);
        databaseHelper.expenseDao().deleteTx(expense);
        Toast.makeText(this, "Expense Deleted Successfully", Toast.LENGTH_SHORT).show();
        id.setText("");
    }

    private void updateExpense() {
        int eid = 0;
        try {
            eid=Integer.parseInt(id.getText().toString());
        } catch (NumberFormatException e)
        {
            Toast.makeText(this, "invalid argument!", Toast.LENGTH_SHORT).show();
            return;
        }
        String titl = title.getText().toString();
        String amoun = amount.getText().toString();
        Expense expense = new Expense();
        expense.setAmount(amoun);
        expense.setTitle(titl);
        expense.setId(eid);
        databaseHelper.expenseDao().updateTx(expense);
        Toast.makeText(this, "updated successfully!", Toast.LENGTH_SHORT).show();
        title.setText("");
        amount.setText("");
        id.setText("");


    }

    private void getExpense() {
        List<Expense> expenses = databaseHelper.expenseDao().getAllExpense();
        adapter=new ExpenseAdapter(expenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void saveExpense() {
        String t = title.getText().toString();
        String a = amount.getText().toString();
        Expense expense = new Expense();
        expense.setTitle(t);
        expense.setAmount(a);
        databaseHelper.expenseDao().addTx(expense);
        Toast.makeText(this, "Expense Save Successfully", Toast.LENGTH_SHORT).show();
        title.setText("");
        amount.setText("");

    }
}