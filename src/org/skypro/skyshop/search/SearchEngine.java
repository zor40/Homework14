package org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.TreeSet;

import org.skypro.skyshop.search.Searchable;


public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public SearchEngine() {
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public TreeSet<Searchable> search(String query) {
        TreeSet<Searchable> results = new TreeSet<>(new SearchableComparator());
        for (Searchable item : items) {
            if (item.getSearchTerm() != null && item.getSearchTerm().contains(query)) {
                results.add(item);
            }
        }
        return results;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isEmpty()) {
            throw new BestResultNotFound("Пустой поисковый запрос");
        }

        Searchable best = null;
        int bestCount = 0;

        for (Searchable item : items) {
            if (item == null) {
                continue;
            }
            String term = item.getSearchTerm();
            if (term != null && term.isEmpty()) {
                continue;
            }

            int count = countOccurrences(term, search);
            if (count > bestCount) {
                bestCount = count;
                best = item;
            }
        }

        if (best == null || bestCount == 0) {
            throw new BestResultNotFound("Не найден подходящий результат для запроса: \"" + search + "\"");
        }

        return best;
    }

    private int countOccurrences(String text, String sub) {
        int count = 0;
        int index = 0;
        while (true) {
            int found = text.indexOf(sub, index);
            if (found == -1) {
                break;
            }
            count++;
            index = found + sub.length();
        }
        return count;
    }
}
