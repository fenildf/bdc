package com.simulation.lab.bdc.enitity;

/**
 * 记录每日一句的基本信息
 */
public class DailySentence {

    private int sid; //每日一句ID

    private String tts; //音频地址

    private String content;//英文内容

    private String note;//中文内容

    private int love;//每日一句喜欢个数

    private String translation;//词霸小编

    private String picture;//图片地址

    private String picture2;//大图片地址

    private String caption;//标题

    private String dateLine;//时间

    private int s_pv;//浏览数

    private int sp_pv;//语音评测浏览数

    private String tags;//相关标签

    private String fenxiang_img;//和成图片

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDateLine() {
        return dateLine;
    }

    public void setDateLine(String dateLine) {
        this.dateLine = dateLine;
    }

    public int getS_pv() {
        return s_pv;
    }

    public void setS_pv(int s_pv) {
        this.s_pv = s_pv;
    }

    public int getSp_pv() {
        return sp_pv;
    }

    public void setSp_pv(int sp_pv) {
        this.sp_pv = sp_pv;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFenxiang_img() {
        return fenxiang_img;
    }

    public void setFenxiang_img(String fenxiang_img) {
        this.fenxiang_img = fenxiang_img;
    }

    @Override
    public String toString() {
        return "DailySentence{" +
                "sid=" + sid +
                ", tts='" + tts + '\'' +
                ", content='" + content + '\'' +
                ", note='" + note + '\'' +
                ", love=" + love +
                ", translation='" + translation + '\'' +
                ", picture='" + picture + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", caption='" + caption + '\'' +
                ", dateLine='" + dateLine + '\'' +
                ", s_pv=" + s_pv +
                ", sp_pv=" + sp_pv +
                ", tags='" + tags + '\'' +
                ", fenxiang_img='" + fenxiang_img + '\'' +
                '}';
    }
}
