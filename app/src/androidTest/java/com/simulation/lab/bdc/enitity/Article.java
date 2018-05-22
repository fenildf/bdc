package com.simulation.lab.bdc.enitity;

public class Article {
    private int articleId; //文章Id;

    private String title; //文章标题

    private String text; //文章内容

    private String translation; //文章翻译

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
