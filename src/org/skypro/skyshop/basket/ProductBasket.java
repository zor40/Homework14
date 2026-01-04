package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private Product[] productBasket = new Product[5];

    public void addToBasket(Product product) {
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] == null) {
                productBasket[i] = product;
                return;
            }
        }
        System.out.println("Невозможно добавить продукт");
    }

    public int getSumOfProducts() {
        int sum = 0;
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] != null) {
                sum += productBasket[i].getPrice();
            }
        }
        return sum;
    }

    public void printProductsOfBasket() {
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] != null) {
                System.out.println(productBasket[i]);
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
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] != null && productBasket[i].getName().equals(nameOfProduct)) {
                return true;
            }
        }
        return false;
    }

    public void cleanBasket() {
        for (int i = 0; i < productBasket.length; i++) {
            productBasket[i] = null;
        }
    }

    public int getCountSpecialProduct() {
        int count = 0;
        for (int i = 0; i < productBasket.length; i++) {
            if (productBasket[i] != null && productBasket[i].isSpecial()) {
                count++;
            }
        }
        return count;
    }
}
