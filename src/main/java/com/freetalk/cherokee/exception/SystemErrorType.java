package com.freetalk.cherokee.exception;

public enum SystemErrorType implements ErrorType {
    /**前3位 000 公共错误, 001: 网关, 002: sns, 003: ts, 004: as, 005:js, 006: os, 007 ofs */
    SYSTEM_ERROR("000500", "操作失败，请重试"),

    ARGUMENT_NOT_VALID("000400", "请求参数校验不通过"),
    INVALID_SERVER_SYSTEMID("000401", "系统id不能为空"),
    INVALID_SERVER_TOKEN("000402", "无效token"),
    INVALID_FILE_PATH("000403", "未检索到合规文件"),
    FILE_NOT_EXISTS("000404", "file not exists");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    SystemErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static SystemErrorType invalidParam(String message) {
        SystemErrorType type = ARGUMENT_NOT_VALID;
        type.setMessage(message);
        return type;
    }
}
