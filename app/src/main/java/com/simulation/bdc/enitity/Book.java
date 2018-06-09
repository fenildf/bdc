package com.simulation.bdc.enitity;

import android.graphics.Bitmap;
import android.util.Log;

import com.simulation.bdc.util.ParseJson;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Book extends DataSupport{

    private int id;//Id
    private int bookId; //教材Id

    private String bookName; //教材名称

    private String coverPicture; //教材封面

    private Grade grade; //教材适用年级

    private Publisher publisher; //出版商信息

    private List<Unit> units; //单元信息

    private int wordNumber;//教材单词总数

    private Bitmap coverPictureBitmap;//教材封面Bitmap;

    public Bitmap getCoverPictureBitmap() {
        return coverPictureBitmap;
    }

    public void setCoverPictureBitmap(Bitmap coverPictureBitmap) {
        this.coverPictureBitmap = coverPictureBitmap;
    }

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

        if(grade == null){
            grade = DataSupport.where("book_id=?" , id + "").findFirst(Grade.class);
        }
        return grade;
    }

    public void setGrade(Grade grade) {
        DataSupport.deleteAll(Grade.class,"book_id=?",id + "");
        grade.save();
        this.grade = grade;
    }

    public Publisher getPublisher() {
        if(publisher == null){
            publisher = DataSupport.where("book_id= ?",id + "").findFirst(Publisher.class);
        }
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        DataSupport.deleteAll(Publisher.class,"book_id=?",id + "");
        publisher.save();
        this.publisher = publisher;
    }

    public List<Unit> getUnits() {
        if(units == null || units.isEmpty()){
            units = DataSupport.where("bookId=?",bookId + "").find(Unit.class);
            Log.d("getUnits: ","bookId=?" + bookId + "" + units);
        }
        return units;
    }

    public void setUnits(List<Unit> units) {
        if(!units.isEmpty()){
//            DataSupport.deleteAll(Unit.class,"bookId=?",bookId + "");
            DataSupport.saveAll(units);
            Log.d("setUnits: ",units.toString());
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
