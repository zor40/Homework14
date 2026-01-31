package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;

public class App {
    public static void main(String[] args) {
        tryTests();

        try {
            ProductBasket basket = new ProductBasket();

            basket.add(new SimpleProduct("egg", 100));
            basket.add(new SimpleProduct("milk", 80));
            basket.add(new DiscountedProduct("cookie", 150, 10));
            basket.add(new SimpleProduct("milk", 80));  // Дубликат milk
            basket.add(new DiscountedProduct("cookie", 150, 10));  // Дубликат cookie

            basket.printBasket();

            System.out.println("\n=== УДАЛЕНИЕ MILK ===");
            List<Product> removedMilk = basket.removeByName("milk");
            System.out.println("Удалено milk: " + removedMilk);
            basket.printBasket();

            System.out.println("\n=== УДАЛЕНИЕ CHICKEN (НЕТ) ===");
            List<Product> removedChicken = basket.removeByName("chicken");
            if (removedChicken.isEmpty()) {
                System.out.println("Список пуст");
            }
            basket.printBasket();

            SearchEngine searchEngine = new SearchEngine();  // Без capacity!
            searchEngine.add(new SimpleProduct("milk", 80));
            searchEngine.add(new DiscountedProduct("cookie", 150, 10));
            searchEngine.add(new FixPriceProduct("vegetable cutter"));
            searchEngine.add(new SimpleProduct("sugar", 60));
            searchEngine.add(new SimpleProduct("egg", 100));
            searchEngine.add(new SimpleProduct("sugar", 60));  // Дубликат
            searchEngine.add(new Article("Ночник", "Ночник для новорожденных с генератором белого шума"));
            searchEngine.add(new Article("Видеоняня", "Видеоняня с монитором, беспроводная 1280 * 720 HD"));

            List<Searchable> result2 = searchEngine.search("coo");
            System.out.println("\nПоиск 'coo':");
            for (Searchable s : result2) {
                System.out.println(s.getStringRepresentation());
            }

            List<Searchable> resultSugar = searchEngine.search("sugar");
            System.out.println("\nПоиск 'sugar' (все): " + resultSugar.size() + " результатов");

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