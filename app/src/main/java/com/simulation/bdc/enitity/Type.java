package com.simulation.bdc.enitity;

import org.litepal.crud.DataSupport;

/**
 * 单词分类
 */
public class Type extends DataSupport{

    private int typeId; //分类Id

    private String typeName;//分类名称

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
