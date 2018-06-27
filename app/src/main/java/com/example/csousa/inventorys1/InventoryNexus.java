package com.example.csousa.inventorys1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.csousa.inventorys1.data.InventoryContract;

import java.util.ArrayList;

public final class InventoryNexus {

    public static long insertData(SQLiteDatabase db, Inventory inventory){
        // Insert into database.

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME, inventory.getProductName());
        values.put(InventoryContract.InventoryEntry.COLUMN_PRICE, inventory.getPrice());
        values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, inventory.getQuantity());
        values.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME, inventory.getSupplierName());
        values.put(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER , inventory.getSupplierPhoneNumber());

        return db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

    }

    public static ArrayList<Inventory> queryData(SQLiteDatabase db){
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryContract.InventoryEntry.COLUMN_PRICE,
                InventoryContract.InventoryEntry.COLUMN_QUANTITY,
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                InventoryContract.InventoryEntry.TABLE_NAME,  // The table to query
                projection,                 // The columns to return
                null,              // The columns for the WHERE clause
                null,           // The values for the WHERE clause
                null,              // Don't group the rows
                null,               // Don't filter by row groups
                null);             // The sort order

        try {
            // Figure out the index of each column
            int idIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry._ID);
            int productNameIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME);
            int priceIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_PRICE);
            int quantityIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY );
            int supplierNameIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneNumberIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            ArrayList<Inventory> books = new ArrayList<>();
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idIndex);
                String productName = cursor.getString(productNameIndex);
                int price = cursor.getInt(priceIndex);
                int quantity = cursor.getInt(quantityIndex);
                String supplierName = cursor.getString(supplierNameIndex);
                String supplierPhoneNumber = cursor.getString(supplierPhoneNumberIndex);

                books.add(new Inventory(currentID, productName,
                        price, quantity, supplierName, supplierPhoneNumber));
            }

            return books;
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    public static long deleteData(SQLiteDatabase db){
        // Delete all.
        return db.delete(InventoryContract.InventoryEntry.TABLE_NAME, null, null);
    }
}
