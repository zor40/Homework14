package org.skypro.skyshop.basket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final List<Product> products = new LinkedList<>();

    public void add(Product product) {
        products.add(product);
    }

    public List<Product> removeByName(String name) {
        List<Product> removed = new LinkedList<>();
        Iterator<Product> iterator = products.iterator();  // ← Iterator!
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removed.add(product);
                iterator.remove();
            }
        }
        return removed;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Итого: " + getSumOfProducts());
        System.out.println("Специальных товаров: " + getCountSpecialProduct());
    }

    public int getSumOfProducts() {
        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public int getCountSpecialProduct() {
        int count = 0;
        for (Product product : products) {
            if (product.isSpecial()) {
                count++;
            }
        }
        return count;
    }

    public void cleanBasket() {
        products.clear();
    }

    public boolean checkProductInBasket(String nameOfProduct) {
        for (Product product : products) {
            if (product.getName().equals(nameOfProduct)) {
                return true;
            }
        }
        return false;
    }
}