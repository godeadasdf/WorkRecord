package com.engine.library.http.entity;

/**
 * Created by Administrator on 2016/7/30.
 * <p/>
 * 参数实体
 */
public class ParamEntity {
    public String key = "";
    public String value = "";

    public ParamEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
