package com.freetalk.cherokee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.freetalk.cherokee.exception.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Result", description = "rest请求的返回模型，所有rest正常都返回该类的对象")
public class ByteResult<T>  {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESSAGE = "Success";
    public static final String ERROR_CODE = "000001";
    public static final String ERROR_MESSAGE = "Error";

    @Schema(description = "处理结果code", required = true)
    private String code;
    @Schema(description = "处理结果描述信息")
    private String message;
    @Schema(description = "返回数据部分的长度")
    private String datalength;
    @Schema(description = "请求状态")
    private String success;
    @Schema(description = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    /**
     * @param errorType
     */
    public ByteResult(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
    }
    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param message
     * @param data
     */
    private ByteResult(String code, String message, T data,int length) {
        this.success = "1";
        this.code = code;
        this.message = message;
        this.data = data;
        this.datalength = String.valueOf(length);
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static ByteResult success(Object data,int length) {
        return new ByteResult<>(SUCCESSFUL_CODE, SUCCESSFUL_MESSAGE, data,length);
    }
    /**
     * 快速创建失败结果并返回结果数据
     *
     * @return Result
     */
    public static ByteResult fail() {
        return new ByteResult<>(SUCCESSFUL_CODE, SUCCESSFUL_MESSAGE, new Object(),0);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getDatalength() {
        return datalength;
    }

    public void setDatalength(String datalength) {
        this.datalength = datalength;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
