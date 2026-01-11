package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private Map<String, List<Product>> productBasketMap = new HashMap<>();
    public void addToBasket(Product product) {
        productBasketMap.computeIfAbsent(product.getName(), k -> new ArrayList<Product>()).add(product);
    }

    public int getSumOfProducts() {
        int sum = 0;
        for (List<Product> productList : productBasketMap.values()) {
            for (Product product : productList) {
                sum += product.getPrice();
            }
        }
        return sum;
    }

    public void printProductsOfBasket() {
        for (List<Product> productList : productBasketMap.values()) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }
        if (getSumOfProducts() != 0) {
            System.out.println("Итого: " + getSumOfProducts());
        } else {
            System.out.println("в корзине пусто");
        }
        System.out.println("Специальных товаров: " + getCountSpecialProduct());
    }

    public boolean checkProductInBasket(String nameOfProduct) {
        return productBasketMap.containsKey(nameOfProduct);
    }

    public void cleanBasket() {
        for (String k : productBasketMap.keySet()) {
            productBasketMap.remove(k);
        }
    }

    public int getCountSpecialProduct() {
        int count = 0;
        for (List<Product> productList : productBasketMap.values()) {
            for (Product product : productList) {
                if (product.isSpecial()) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Product> removeFromBasketByName(String name) {
        return productBasketMap.remove(name);
    }
}