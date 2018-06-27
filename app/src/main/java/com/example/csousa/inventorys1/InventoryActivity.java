package com.example.csousa.inventorys1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csousa.inventorys1.data.InventoryContract;
import com.example.csousa.inventorys1.data.InventoryDbHelper;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new InventoryDbHelper(this);


        //Add book activity_inventory.xml click listener
        LinearLayout addBook = findViewById(R.id.add_layout);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        //Get book activity_inventory.xml click listener
        LinearLayout getBook = findViewById(R.id.get_layout);
        getBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatabaseInfo();
            }
        });

        //Del book activity_inventory.xml click listener
        LinearLayout delBook = findViewById(R.id.del_layout);
        delBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

    }

    private void insertData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        Inventory book = new Inventory("Chicken", 18,
                300, "KFC",
                "+35191432100");

        ShowToast(InventoryNexus.insertData(db, book), "ADD");

        book = new Inventory("Hijack", 29,
                145, "FBI",
                "+35199555444");

        ShowToast(InventoryNexus.insertData(db, book), "ADD");
    }

    private void displayDatabaseInfo(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ArrayList<Inventory> books = InventoryNexus.queryData(db);

        TextView displayView = (TextView) findViewById(R.id.books);
        displayView.setText("The inventory table contains " + books.toArray().length + " books.\n\n");
        displayView.append("\n" + InventoryContract.InventoryEntry._ID + " - " +
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME + " - " +
                InventoryContract.InventoryEntry.COLUMN_PRICE + " - " +
                InventoryContract.InventoryEntry.COLUMN_QUANTITY + " - " +
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME + "\n");

        for (Inventory book:books)
        {
            displayView.append(book.getInventory());
        }

    }

    private void deleteData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ShowToast(InventoryNexus.deleteData(db), "DEL");
    }


    private void ShowToast(long newRowId, String operationType){
        // Show a toast message depending on whether or not it was successful
        if (newRowId == -1) {
            switch (operationType) {
                case "DEL": Toast.makeText(this, "Error with deleting all books", Toast.LENGTH_SHORT).show();
                    break;
                case "ADD": Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
                    break;
                default: Toast.makeText(this, "Error processing database operation", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else {
            switch (operationType) {
                case "DEL": Toast.makeText(this, "All books deleted ", Toast.LENGTH_SHORT).show();
                    break;
                case "ADD": Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
                    break;
                default: Toast.makeText(this, "Successful operation", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }





}
