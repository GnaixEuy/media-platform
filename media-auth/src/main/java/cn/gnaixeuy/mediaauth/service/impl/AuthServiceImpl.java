package cn.gnaixeuy.mediaauth.service.impl;

import cn.gnaixeuy.mediaauth.config.RedisConfig;
import cn.gnaixeuy.mediaauth.service.AuthService;
import cn.gnaixeuy.mediaauth.utils.SmsUtils;
import cn.gnaixeuy.mediaauth.utils.ValidateCodeUtils;
import cn.gnaixeuy.mediacommon.enmus.ExceptionType;
import cn.gnaixeuy.mediacommon.enmus.RedisDbType;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("#{T(java.lang.Integer).parseInt('${phone.verification.length:6}')}")
    private Integer phoneVerificationCodeLength;
    @Value("#{T(java.lang.Integer).parseInt('${phone.verification.live:300}')}")
    private Integer phoneVerificationCodeLiveTime;
    private RedisConfig redisConfig;

    /**
     * 请求手机验证码
     *
     * @param phoneNumber 手机号码
     * @return 业务是否成功
     */
    @Override
    public boolean getPhoneVerificationCode(String phoneNumber) {
        if (StrUtil.isBlank(phoneNumber)) {
            throw new BizException(ExceptionType.DATA_IS_EMPTY);
        }
        RedisTemplate<String, Object> redisTemplateByDb = this.redisConfig.getRedisTemplateByDb(RedisDbType.PHONE_VERIFICATION_CODE.getCode());
        if (ObjectUtil.isNotNull(redisTemplateByDb.opsForValue().get(phoneNumber))) {
            throw new BizException(ExceptionType.PHONE_VERIFICATION_EXIT);
        }
        Integer verificationCode = ValidateCodeUtils.generateValidateCode(this.phoneVerificationCodeLength);
        log.info(phoneNumber + " verificationCode: " + verificationCode);
        redisTemplateByDb.opsForValue().set(phoneNumber, verificationCode, this.phoneVerificationCodeLiveTime, TimeUnit.SECONDS);
        try {
            //TODO 发送功能已正常，模版未申请
            SmsUtils.sendMessage("阿里云短信测试", "SMS_154950909", phoneNumber, String.valueOf(verificationCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Autowired
    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

}
