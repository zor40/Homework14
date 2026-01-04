package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        basket.addToBasket(new Product("egg") {
            @Override
            public boolean isSpecial() {
                return false;
            }
        });
        basket.addToBasket(new SimpleProduct("egg", 100));


        basket.addToBasket(new Product("milk") {
            @Override
            public boolean isSpecial() {
                return false;
            }
        });
        basket.addToBasket(new Product("cookie") {
            @Override
            public boolean isSpecial() {
                return false;
            }
        });
        basket.addToBasket(new Product("salt") {
            @Override
            public boolean isSpecial() {
                return false;
            }
        });
        basket.addToBasket(new Product("sugar") {
            @Override
            public boolean isSpecial() {
                return false;
            }
        });
        basket.addToBasket(new SimpleProduct("milk", 80));
        basket.addToBasket(new DiscountedProduct("cookie", 150, 10));
        basket.addToBasket(new FixPriceProduct("vegetable cutter"));
        basket.addToBasket(new SimpleProduct("sugar", 60));


        basket.addToBasket(new Product("sugar") {
            @Override
            public boolean isSpecial() {
                return false;
            }
        });
        basket.addToBasket(new SimpleProduct("sugar", 60));


        basket.printProductsOfBasket();
    }
}
