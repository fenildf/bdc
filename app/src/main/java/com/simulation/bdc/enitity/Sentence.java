package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

/**
 * 例句信息
 */
public class Sentence extends DataSupport{

    private String text; //例句英文

    private String translation;//例句翻译

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "text='" + text + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
