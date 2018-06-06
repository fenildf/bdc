package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Review extends DataSupport{

    private int reviewId; //复习表Id

    private int userId;//用户Id

    private Date addTime;//添加时间

    private int reviewTime;//复习次数

    private int wordId;//单词Id

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(int reviewTime) {
        this.reviewTime = reviewTime;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", userId=" + userId +
                ", addTime=" + addTime +
                ", reviewTime=" + reviewTime +
                ", wordId=" + wordId +
                '}';
    }
}
