package com.fh.enums;

import java.util.HashMap;
import java.util.Map;

//内务评分分数显示类型
public enum PlanTypeEnum {
	Ios(0, "IOS"),
	Android(1, "安卓"),
	Image(2, "图库"),
	PC(3, "PC"),
	Wap(4, "Wap"),
	WapV3(6, "WapV3"),
	Tuku(5, "Tuku");
    private int key;
    private String text;
    
    PlanTypeEnum(int key, String text) {
        this.key = key;
        this.text = text;
    }
    
    public String toString() {
        return String.valueOf(this.key);
    }
    
    public static String getText(int key) {
        for (PlanTypeEnum type : PlanTypeEnum.values()) {
            if(type.key == key) {
                return type.text;
            }
        }
        return "";
    }
    
    public static Map<Integer, String> texts() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(PlanTypeEnum type : PlanTypeEnum.values()) {
            map.put(type.key, type.text);
        }
        return map;
    }
    public int getKey() {
        return this.key;
    }
}
