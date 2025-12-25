package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products = new Product[5];
    private int size = 0;

    public void add(Product product) {
        if (size >= products.length) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        products[size++] = product;
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            if (products[i] != null) {
                total += products[i].getPrice();
            }
        }
        return total;
    }

    public void print() {
        if (size == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (products[i] != null) {
                System.out.println(products[i].getName() + ": " + products[i].getPrice());
            }
        }
        System.out.println("Итого: " + getTotalPrice());
    }

    public boolean contains(String productName) {
        for (int i = 0; i < size; i++) {
            if (products[i] != null && products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            products[i] = null;
        }
        size = 0;
    }
}

