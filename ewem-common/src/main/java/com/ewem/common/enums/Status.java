package com.ewem.common.enums;

/**
 * @author ewem
 * <p>
 * 状态
 */
public enum Status {

    NORMAL("0", "正常"),

    DISABLE("1", "停用");

    private final String code;
    private final String info;

    Status(String code, String info) {
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
