package com.fh.enums;

import java.util.HashMap;
import java.util.Map;

//内务评分分数显示类型
public enum VideoDataTypeEnum {
	BannerType(1, "Banner数据类型"),
	VideoDataType(2, "视频数据类型"),
	TryDataType(3, "试播类型");
    private int key;
    private String text;
    
    VideoDataTypeEnum(int key, String text) {
        this.key = key;
        this.text = text;
    }
    
    public String toString() {
        return String.valueOf(this.key);
    }
    
    public static String getText(int key) {
        for (VideoDataTypeEnum type : VideoDataTypeEnum.values()) {
            if(type.key == key) {
                return type.text;
            }
        }
        return "";
    }
    
    public static Map<Integer, String> texts() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(VideoDataTypeEnum type : VideoDataTypeEnum.values()) {
            map.put(type.key, type.text);
        }
        return map;
    }
    public int getKey() {
        return this.key;
    }
}
