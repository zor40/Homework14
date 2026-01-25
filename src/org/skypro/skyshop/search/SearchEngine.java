package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
    }

    public void add(Searchable item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        for (int i = 0; i < items.length && resultCount < 5; i++) {
            Searchable current = items[i];
            if (current == null) {
                continue;
            }
            String term = current.getSearchTerm();
            if (term != null && term.contains(query)) {
                results[resultCount] = current;
                resultCount++;
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
            if (term == null || term.isEmpty()) {
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
