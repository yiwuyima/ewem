package com.ewem.common.enums;

/**
 * @author ewem
 * <p>
 * 删除标志
 */
public enum DelFlag {

    EXIST("0", "存在"),

    DELETE("2", "删除");

    private final String code;
    private final String info;

    DelFlag(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
