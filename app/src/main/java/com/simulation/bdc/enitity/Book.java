package com.simulation.bdc.enitity;

import java.util.List;

public class Book {

    private int bookId; //教材Id

    private String bookName; //教材名称

    private String coverPicture; //教材封面

    private String grade; //教材适用年级

    private Publisher publisher; //出版商信息

    private List<Unit> units; //单元信息

    private int wordNumber;//教材单词总数

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", coverPicture='" + coverPicture + '\'' +
                ", grade='" + grade + '\'' +
                ", publisher=" + publisher +
                ", units=" + units +
                ", wordNumber=" + wordNumber +
                '}';
    }
}
