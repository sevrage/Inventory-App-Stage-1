package com.example.csousa.inventorys1;

public class Inventory {

    private int id;
    private String productName;
    private int price;
    private int quantity;
    private String supplierName;
    private String supplierPhoneNumber;

    public Inventory(final String productName,
                     final int price, final int quantity,
                     final String supplierName, final String supplierPhoneNumber) {
        this.id = -1;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public Inventory(final int id, final String productName,
                     final int price, final int quantity,
                     final String supplierName, final String supplierPhoneNumber) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public String getInventory(){
        return "\n" + this.id + " - " +
               this.productName + " - " +
               this.price + " - " +
               this.quantity + " - " +
               this.supplierName + " - " +
               this.supplierPhoneNumber;
    }

}
