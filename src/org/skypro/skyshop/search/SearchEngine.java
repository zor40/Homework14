package org.skypro.skyshop.search;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    private final java.util.Comparator<Searchable> comparator =
            java.util.Comparator.comparing(Searchable::getSearchTerm);

    public SearchEngine() {
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public TreeSet<Searchable> search(String query) {
        return items.stream()
                .filter(s -> {
                    String term = s.getSearchTerm();
                    return term != null
                            && !term.isEmpty()
                            && term.toLowerCase().contains(query.toLowerCase());
                })
                .collect(java.util.stream.Collectors.toCollection(
                        () -> new java.util.TreeSet<>(comparator)
                ));
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