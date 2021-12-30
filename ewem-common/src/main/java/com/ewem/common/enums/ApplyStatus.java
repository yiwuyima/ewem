package com.ewem.common.enums;

/**
 * @author ewem
 * <p>
 * 码申请状态
 */
public enum ApplyStatus {

    INIT("1", "初始化"),

    EXECUTING("2", "进行中"),

    SUCCESS("3", "成功"),

    ERROR("4", "失败");

    private final String code;
    private final String info;

    ApplyStatus(String code, String info) {
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
