package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Unit extends DataSupport{

    private int unitId; //单元Id

    private int bookId;//教材Id

    private String unitName;//单元名称

    private List<Word> words; //单元单词

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", bookId=" + bookId +
                ", unitName='" + unitName + '\'' +
                ", words=" + words +
                '}';
    }
}
