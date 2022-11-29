package cn.gnaixeuy.mediauaa.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/27
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public class WebResponseTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {
        exception.printStackTrace();
        if (exception instanceof InternalAuthenticationServiceException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomOauthException("账号密码错误"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomOauthException(exception.getMessage()));
    }
}

