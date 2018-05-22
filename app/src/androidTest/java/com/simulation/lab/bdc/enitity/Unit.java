package com.simulation.lab.bdc.enitity;

import java.util.List;

public class Unit {

    private int unitId; //单元Id

    private String unitName;//单元名称

    private List<Word> words; //单元单词

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
                ", unitName='" + unitName + '\'' +
                ", words=" + words +
                '}';
    }
}
