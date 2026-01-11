package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {

    private Map<String, Searchable> searchableMap = new TreeMap<>();
    private List<Searchable> searchables = new ArrayList<>();

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Map<String, Searchable> search(String query) {
        for (Searchable searchable : searchables) {
            if (searchable.getSearchTerm().contains(query)) {
                searchableMap.put(searchable.getName(), searchable);
            }
        }
        return searchableMap;
    }

    public Searchable search2(String substring) {
        int count = 0;
        Searchable result = null;
        for (Searchable a : searchables) {
            String str = a.getSearchTerm();
            int quantity = 0;
            int index = 0;
            int indexString = str.indexOf(substring, index);
            while (indexString != -1) {
                quantity++;
                index = indexString + substring.length();
                indexString = str.indexOf(substring, index);
            }
            if (quantity > count) {
                count = quantity;
                result = a;
            }
            if (result == null) {
            }
        }
        return result;
    }
}


