package com.kaushlendraprajapati.roomdbpractice;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Expense.class, exportSchema = false, version = 2)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract ExpenseDao expenseDao();
}
