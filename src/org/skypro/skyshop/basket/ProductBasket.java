package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addToBasket(Product product) {
        String name = product.getName();
        List<Product> list = products.computeIfAbsent(name, k -> new ArrayList<>());
        list.add(product);
    }

    public boolean removeFromBasketByName(String name) {
        return products.remove(name) != null;
    }

    public void printProductsOfBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        int totalPrice = 0;
        int specialCount = 0;

        for (List<Product> list : products.values()) {

            for (Product p : list) {
                System.out.println(p.toString());
                totalPrice += p.getPrice();
                if (p.isSpecial()) {
                    specialCount++;
                }
            }
        }

        System.out.println("Итого: " + totalPrice);
        System.out.println("Специальных товаров: " + specialCount);
    }

    public int getCountSpecialProduct() {
        int count = 0;
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                if (p.isSpecial()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void cleanBasket() {
        products.clear();
    }
}