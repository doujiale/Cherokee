package com.freetalk.cherokee.interceptor;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.freetalk.cherokee.exception.BaseException;
import com.freetalk.cherokee.exception.SystemErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Boolean checkServerToken = false;
    private String cryptoKey = "l7y36xflLd4GLXxKh7BQbA==";
    private String serverToken = "yuntUT0ken";
    private SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, Base64.decode(cryptoKey));

    public UserInterceptor() {
    }
    public UserInterceptor(Boolean checkServerToken) {
        this.checkServerToken = checkServerToken;
    }

    /**
     * 系统id
     */
    public static final String SYSTEM_ID = "systemid";
    /**
     * 认证token
     */
    public static final String CHEROKEE_TOKEN = "dzjz_token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/swagger") || servletPath.startsWith("/webjars")) {
            return true;
        }
//        if(StringUtils.isEmpty(request.getHeader(SYSTEM_ID))){
//            throw new BaseException(SystemErrorType.INVALID_SERVER_SYSTEMID);
//        }
        if (checkServerToken) {
            if(checkToken(request.getHeader(CHEROKEE_TOKEN))) {
                return true;
            } else {
                throw new BaseException(SystemErrorType.INVALID_SERVER_TOKEN);
            }
        } else {
            return true;
        }
    }

    private boolean checkToken(String token) {
        log.debug("校验token:{}", token);
        //解密为字符串
        String decryptStr = null;
        try {
            decryptStr = aes.decryptStr(token, CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            log.error("token:" + token + " check error", e);
        }
        if (serverToken.equals(decryptStr)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
