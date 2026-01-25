package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или состоять из пробелов");
        }
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getSearchTerm() {
        return getName();
    }

    @Override
    public String getType() {
        return "PRODUCT";
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();


    @Override
    public String toString() {
        return name + ": " + getPrice();
    }
}



