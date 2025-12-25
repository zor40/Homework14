package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();
        Product bread = new Product("Хлеб", 50);
        basket.add(bread);
        Product milk = new Product("Молоко", 80);
        Product cheese = new Product("Сыр", 200);
        Product butter = new Product("Масло", 150);
        Product eggs = new Product("Яйца", 120);
        basket.add(milk);
        basket.add(cheese);
        basket.add(butter);
        basket.add(eggs);
        Product apple = new Product("Яблоки", 100);
        basket.add(apple);
        basket.print();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Есть хлеб? " + basket.contains("Хлеб"));
        System.out.println("Есть яблоки? " + basket.contains("Яблоки"));
        basket.clear();
        basket.print();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Есть хлеб? " + basket.contains("Хлеб"));
    }
}

