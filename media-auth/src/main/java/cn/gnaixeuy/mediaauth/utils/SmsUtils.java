package cn.gnaixeuy.mediaauth.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
@Component
public class SmsUtils {

    private static String ACCESSKEYID;
    private static String SECRET;

    @Value(value = "${aliyun.accessKeyId}")
    public String preACCESSKEYID;
    @Value(value = "${aliyun.secret}")
    public String preSECRET;

    public static Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(SmsUtils.ACCESSKEYID)
                .setAccessKeySecret(SmsUtils.SECRET);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    public static void sendMessage(String signName, String templateCode, String phoneNumbers, String param) throws Exception {
        Client client = SmsUtils.createClient();
        SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumbers)
                .setTemplateParam("{\"code\":" + param + "}");
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            SendSmsResponseBody body = sendSmsResponse.getBody();
        } catch (TeaException error) {
            SmsUtils.log.error(Common.assertAsString(error.message));
        } catch (Exception error) {
            TeaException error1 = new TeaException(error.getMessage(), error);
            SmsUtils.log.error(Common.assertAsString(error1.message));
        }
    }

    @PostConstruct
    public void init() {
        SmsUtils.ACCESSKEYID = this.preACCESSKEYID;
        SmsUtils.SECRET = this.preSECRET;
    }


}
