package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;

public final class Article implements Searchable {
    private String title;
    private String article;

    public Article(String title, String article) {
        this.title = title;
        this.article = article;
    }

    public String toString() {
        return this.title + "'" + this.article;
    }

    public String getName() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return this.article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String getSearchTerm() {
        return title + " " + article;
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}



