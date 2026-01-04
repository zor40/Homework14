package org.skypro.skyshop.product;

public abstract class Product {
    private String name;
    private int price;

    public Product(String name) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public abstract boolean isSpecial();

    @Override
    public String toString() {
        return name + ": " + price;
    }
}

