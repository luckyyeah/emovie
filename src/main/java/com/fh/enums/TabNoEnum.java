package com.fh.enums;

import java.util.HashMap;
import java.util.Map;

//内务评分分数显示类型
public enum TabNoEnum {
	Banner(1, "热门推荐"),
	Home(2, "首页"),
	Colum(3, "频道"),
	VIP(4, "VIP"),
	Select(5, "精选");
    private int key;
    private String text;
    
    TabNoEnum(int key, String text) {
        this.key = key;
        this.text = text;
    }
    
    public String toString() {
        return String.valueOf(this.key);
    }
    
    public static String getText(int key) {
        for (TabNoEnum type : TabNoEnum.values()) {
            if(type.key == key) {
                return type.text;
            }
        }
        return "";
    }
    
    public static Map<Integer, String> texts() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(TabNoEnum type : TabNoEnum.values()) {
            map.put(type.key, type.text);
        }
        return map;
    }
    public int getKey() {
        return this.key;
    }
}
