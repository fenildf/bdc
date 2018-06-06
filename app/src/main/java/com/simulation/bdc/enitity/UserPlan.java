package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 用户计划
 */
public class UserPlan extends DataSupport{

    private long id;//主键

    private int planId; //用户计划Id

    private int isDoing; //是否正在进行

    private int wordNumber; //计划每日背诵单词数

    private int hasDone; //已背诵单词数

    private int wordId; //已背诵的单词

    private int unitId;//已背诵到的单元

    private Book book; //计划教材

    private int bookId;//计划教材Id

    private int userId;//用户Id

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
        if(book == null){
            book = DataSupport.where("bookId=?",bookId + "").findFirst(Book.class);
            if(book != null) {
                book.setUnits(DataSupport.where("bookId=?", book.getBookId() + "").find(Unit.class));
            }
        }
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        if(book != null) {
            setBookId(book.getBookId());
            book.save();
        }
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "id=" + id +
                ", planId=" + planId +
                ", isDoing=" + isDoing +
                ", wordNumber=" + wordNumber +
                ", hasDone=" + hasDone +
                ", wordId=" + wordId +
                ", unitId=" + unitId +
                ", book=" + book +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
