package com.fh.enums;

import java.util.HashMap;
import java.util.Map;

//内务评分分数显示类型
public enum CodeTypeEnum {
	TabDataType(1, "频道数据类型"),
	ColumnDataType(2, "栏目数据类型"),
	VideoDataType(3, "视频数据类型"),
	AdvertisementDataType(4, "广告数据类型"),
	ShowTypeoDataType(5, "广告显示类型类型"),
	PushType(6, "推送类型"),
	DownloadStatusType(7, "下载状态类型"),
	Image(2, "图库");
    private int key;
    private String text;
    
    CodeTypeEnum(int key, String text) {
        this.key = key;
        this.text = text;
    }
    
    public String toString() {
        return String.valueOf(this.key);
    }
    
    public static String getText(int key) {
        for (CodeTypeEnum type : CodeTypeEnum.values()) {
            if(type.key == key) {
                return type.text;
            }
        }
        return "";
    }
    
    public static Map<Integer, String> texts() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(CodeTypeEnum type : CodeTypeEnum.values()) {
            map.put(type.key, type.text);
        }
        return map;
    }
    public int getKey() {
        return this.key;
    }
}
