package com.simulation.bdc.enitity;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.SearchView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Word extends DataSupport implements Parcelable {

    private long id;//主键

    private int wordId; //单词Id

    private String proUk; //英式发音地址

    private String proUsa; //美式发音地址

    private String phUk; //英式发音音标

    private String phUsa; //美式发音音标

    private String wordName; //单词拼写

    private List<Mean> means; //单词释义

    private List<Sentence> sentence; //单词例句

    private List<String> alikeWord;// 相似单词

    private List<String> sameTypeWord;// 同种类型单词


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProUk() {
        return proUk;
    }

    public void setProUk(String proUk) {
        this.proUk = proUk;
    }

    public String getProUsa() {
        return proUsa;
    }

    public void setProUsa(String proUsa) {
        this.proUsa = proUsa;
    }

    public String getPhUk() {
        return phUk;
    }

    public void setPhUk(String phUk) {
        this.phUk = phUk;
    }

    public String getPhUsa() {
        return phUsa;
    }

    public void setPhUsa(String phUsa) {
        this.phUsa = phUsa;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public List<Mean> getMeans() {
        return means;
    }

    public void setMeans(List<Mean> means) {
        this.means = means;
    }

    public List<Sentence> getSentence() {
        return sentence;
    }

    public void setSentence(List<Sentence> sentence) {
        this.sentence = sentence;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public List<String> getAlikeWord() {
        return alikeWord;
    }

    public void setAlikeWord(List<String> alikeWord) {
        this.alikeWord = alikeWord;
    }

    public List<String> getSameTypeWord() {
        return sameTypeWord;
    }

    public void setSameTypeWord(List<String> sameTypeWord) {
        this.sameTypeWord = sameTypeWord;
    }

    public static final Parcelable.Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel source) {
            Word word = new Word();
            word.setWordId(source.readInt());
            word.setProUk(source.readString());
            word.setPhUsa(source.readString());
            word.setPhUk(source.readString());
            word.setPhUsa(source.readString());
            word.setWordName(source.readString());
            word.setMeans(source.readArrayList(Mean.class.getClassLoader()));
            word.setSentence(source.readArrayList(Sentence.class.getClassLoader()));
            return word;
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[0];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(wordId);
        dest.writeString(proUk);
        dest.writeString(proUsa);
        dest.writeString(phUk);
        dest.writeString(phUsa);
        dest.writeString(wordName);
        dest.writeList(means);
        dest.writeList(sentence);
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", proUk='" + proUk + '\'' +
                ", proUsa='" + proUsa + '\'' +
                ", phUk='" + phUk + '\'' +
                ", phUsa='" + phUsa + '\'' +
                ", wordName='" + wordName + '\'' +
                ", means=" + means +
                ", sentence=" + sentence +
                ", alikeWord=" + alikeWord +
                ", sameTypeWord=" + sameTypeWord +
                '}';
    }
}
