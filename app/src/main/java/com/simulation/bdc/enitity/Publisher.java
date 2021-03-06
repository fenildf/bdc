package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

/**
 * 出版商信息
 */
public class Publisher extends DataSupport{

    public int publisherId; //出版商Id

    private String publisherName; //出版商名称

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", publisherName='" + publisherName + '\'' +
                '}';
    }
}
