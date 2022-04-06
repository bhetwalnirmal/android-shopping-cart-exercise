package com.test.customizelistview;

public class Cart {
    private Product product;
    private int quantity;

    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductTotal () {
        return this.getQuantity() * this.getProduct().getPrice();
    }
}
