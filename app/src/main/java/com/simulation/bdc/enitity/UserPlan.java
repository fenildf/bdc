package com.simulation.bdc.enitity;

/**
 * 用户计划
 */
public class UserPlan {

    private int planId; //用户计划Id

    private int isDoing; //是否正在进行

    private int wordNumber; //计划每日背诵单词数

    private int hasDone; //已背诵单词数

    private int wordId; //已背诵的单词

    private int unitId;//已背诵到的单元

    private Book book; //计划教材

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getIsDoing() {
        return isDoing;
    }

    public void setIsDoing(int isDoing) {
        this.isDoing = isDoing;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    public int getHasDone() {
        return hasDone;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public void setHasDone(int haoDone) {
        this.hasDone = haoDone;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "planId=" + planId +
                ", isDoing=" + isDoing +
                ", wordNumber=" + wordNumber +
                ", hasDone=" + hasDone +
                ", wordId=" + wordId +
                ", unitId=" + unitId +
                ", book=" + book +
                '}';
    }
}
