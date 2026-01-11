package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        try {
            ProductBasket basket = new ProductBasket();
            basket.addToBasket(new SimpleProduct("egg", 100));
            basket.addToBasket(new SimpleProduct("milk", 80));
            basket.addToBasket(new DiscountedProduct("cookie", 150, 10));
            basket.addToBasket(new SimpleProduct("milk", 80));
            basket.addToBasket(new DiscountedProduct("cookie", 150, 10));
            basket.printProductsOfBasket();
            System.out.println(basket.removeFromBasketByName("milk"));
            basket.printProductsOfBasket();
            System.out.println(basket.removeFromBasketByName("chicken"));
            basket.printProductsOfBasket();
            SearchEngine searchEngine = new SearchEngine(20);
            searchEngine.add(new SimpleProduct("milk", 80));
            searchEngine.add(new DiscountedProduct("cookie", 150, 10));
            searchEngine.add(new FixPriceProduct("vegetable cutter"));
            searchEngine.add(new SimpleProduct("sugar", 60));
            searchEngine.add(new SimpleProduct("egg", 100));
            searchEngine.add(new SimpleProduct("sugar", 60));
            searchEngine.add(new Article("Ночник", "Ночник для новорожденных с генератором белого шума"));
            searchEngine.add(new Article("Видеоняня", "Видеоняня с монитором, беспроводная 1280 * 720 HD"));
            Searchable[] result2 = searchEngine.search("coo");
            if (result2[0] != null) {
                System.out.println(result2[0].getStringRepresentation());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Введены не корректные данные!");
        }
    }
}
