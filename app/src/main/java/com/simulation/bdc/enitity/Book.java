package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Book extends DataSupport{


    private int bookId; //教材Id

    private String bookName; //教材名称

    private String coverPicture; //教材封面

    private Grade grade; //教材适用年级

    private Publisher publisher; //出版商信息

    private List<Unit> units; //单元信息

    private int wordNumber;//教材单词总数



//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
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
        if(!units.isEmpty()){
            for (int i = 0;i < units.size();i++){
                units.get(i).save();
            }
        }
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
