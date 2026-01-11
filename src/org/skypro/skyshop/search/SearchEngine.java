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
}
