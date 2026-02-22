package org.skypro.skyshop.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable a, Searchable b) {
        // Длинное имя ПЕРЕД коротким
        int lenCmp = Integer.compare(b.getName().length(), a.getName().length());
        if (lenCmp != 0) {
            return lenCmp;
        }
        // Равная длина → алфавит
        return a.getName().compareTo(b.getName());
    }
}