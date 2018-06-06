package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

public class Grade extends DataSupport{

    private int gradeId;//年级Id

    private String grandeName;//年级名称

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGrandeName() {
        return grandeName;
    }

    public void setGrandeName(String grandeName) {
        this.grandeName = grandeName;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", grandeName='" + grandeName + '\'' +
                '}';
    }
}
