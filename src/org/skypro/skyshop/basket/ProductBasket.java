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

        int totalPrice = products.values().stream()
                .flatMap(java.util.Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();

        products.values().stream()
                .flatMap(java.util.Collection::stream)
                .forEach(p -> System.out.println(p.toString()));

        System.out.println("Итого: " + totalPrice);
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    // Если нужен публичный метод:
    public int getCountSpecialProduct() {
        return (int) getSpecialCount();
    }

    // Метод по требованию ДЗ
    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(java.util.Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void cleanBasket() {
        products.clear();
    }
}