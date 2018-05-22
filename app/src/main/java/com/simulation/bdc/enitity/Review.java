package com.simulation.bdc.enitity;

import java.util.Date;

public class Review {

    private int reviewId; //复习表Id

    private int userId;//用户Id

    private Date addTime;//添加时间

    private int reviewTime;//复习次数

    private Word word;//单词

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

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", userId=" + userId +
                ", addTime=" + addTime +
                ", reviewTime=" + reviewTime +
                ", word=" + word +
                '}';
    }
}
