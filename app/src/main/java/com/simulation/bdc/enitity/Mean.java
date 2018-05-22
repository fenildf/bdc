package com.simulation.bdc.enitity;

public class Mean {

    private String mean; //单词释义

    private String part; //词性

    /**
     * @return the mean
     */
    public String getMean() {
        return mean;
    }

    /**
     * @param mean the mean to set
     */
    public void setMean(String mean) {
        this.mean = mean;
    }

    /**
     * @return the part
     */
    public String getPart() {
        return part;
    }


    /**
     * @param part the part to set
     */
    public void setPart(String part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return "Mean{" +
                "mean='" + mean + '\'' +
                ", part='" + part + '\'' +
                '}';
    }


}
