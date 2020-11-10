package com.freetalk.cherokee.exception;

public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回message
     *
     * @return
     */
    String getMessage();
}
