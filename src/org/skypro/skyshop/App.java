package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.TreeSet;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        tryTests();

        try {
            ProductBasket basket = new ProductBasket();

            basket.addToBasket(new SimpleProduct("egg", 100));
            basket.addToBasket(new SimpleProduct("milk", 80));
            basket.addToBasket(new DiscountedProduct("cookie", 150, 10));
            basket.addToBasket(new SimpleProduct("milk", 80));  // Дубликат milk
            basket.addToBasket(new DiscountedProduct("cookie", 150, 10));  // Дубликат cookie

            basket.printProductsOfBasket();

            System.out.println("\n=== УДАЛЕНИЕ MILK ===");
            boolean removedMilk = basket.removeFromBasketByName("milk");
            System.out.println("Удалено milk? " + removedMilk);
            basket.printProductsOfBasket();

            System.out.println("\n=== УДАЛЕНИЕ CHICKEN (НЕТ) ===");
            boolean removedChicken = basket.removeFromBasketByName("chicken");
            System.out.println("Удалено chicken? " + removedChicken);
            basket.printProductsOfBasket();

            SearchEngine searchEngine = new SearchEngine();
            searchEngine.add(new SimpleProduct("milk", 80));
            searchEngine.add(new DiscountedProduct("cookie", 150, 10));
            searchEngine.add(new FixPriceProduct("vegetable cutter"));
            searchEngine.add(new SimpleProduct("sugar", 60));
            searchEngine.add(new SimpleProduct("egg", 100));
            searchEngine.add(new SimpleProduct("sugar", 60));  // Дубликат
            searchEngine.add(new Article("Ночник", "Ночник для новорожденных с генератором белого шума"));
            searchEngine.add(new Article("Видеоняня", "Видеоняня с монитором, беспроводная 1280 * 720 HD"));

            System.out.println("\nПоиск 'coo':");
            TreeSet<Searchable> result2 = searchEngine.search("coo");
            for (Searchable s : result2) {
                System.out.println(s.getStringRepresentation());
            }

            System.out.println("\nПоиск 'sugar':");
            TreeSet<Searchable> resultSugar = searchEngine.search("sugar");
            System.out.println("Результатов: " + resultSugar.size());
            for (Searchable s : resultSugar) {
                System.out.println(s.getStringRepresentation());
            }

            try {
                Searchable best = searchEngine.findBestMatch("sugar");
                System.out.println("Лучший для 'sugar': " + best.getStringRepresentation());
            } catch (BestResultNotFound e) {
                System.out.println(e.getMessage());
            }

            try {
                Searchable best = searchEngine.findBestMatch("abcdxyz");
                System.out.println("Лучший для 'abcdxyz': " + best.getStringRepresentation());
            } catch (BestResultNotFound e) {
                System.out.println(e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Введены не корректные данные!");
        }
    }

    private static void tryTests() {
        try {
            new SimpleProduct("   ", 100);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new SimpleProduct("egg", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new DiscountedProduct("cookie", -150, 10);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new DiscountedProduct("cookie", 150, 150);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new Article("", "Текст статьи");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}