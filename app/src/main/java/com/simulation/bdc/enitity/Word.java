package com.simulation.bdc.enitity;

import java.util.List;

public class Word {

    private int wordId; //单词Id

    private String proUk; //英式发音地址

    private String proUsa; //美式发音地址

    private String phUk; //英式发音音标

    private String phUsa; //美式发音音标

    private String wordName; //单词拼写

    private List<Mean> means; //单词释义

    private List<Sentence> sentence; //单词例句


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
                '}';
    }
}
