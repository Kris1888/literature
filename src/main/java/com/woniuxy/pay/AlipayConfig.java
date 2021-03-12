package com.woniuxy.pay;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取
 *
 * @author 小道仙
 * @date 2020年2月18日
 */
@Configuration

//@PropertySource("classpath:config/alipay.properties")
@Data
@Component
public class AlipayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    @Value("2021000117620146")
    private String appId;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    @Value("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJqgUUYhRv0HrQpgerJEKsw96o46CPKNGbMdZMP9OnLTz2bc99Af1yYtWDIRT2kznHBF9jwfGumxnJVzIi98RJIAXZ/nQ7Yj3yCMLRIOlTUesR4zOBtddvUNlO31bA42qWn5bPA0RRAtfg/ScUpsP/GvKKjQW4J9zOTarIt9Eah12IwC+ER4/yTQ+qDGwRcXQ4wWlcTO+u4AbOOqRMeTdDGPu/E/VrKKpclqkG2MMCPk8wJdLVghYk+eKq1sCIMIVofnkMfDeNa47csfX2QLzPOcp8lHcERFG4mTsRCjVSIiXCvlJA3L3+vI7T12sqcMdEogc8RJHip3Q0uzgUnFQjAgMBAAECggEAck/wL+DRv5eKpD96Fq7hfryW4/9AkCABDbHwlRhbFHMIMEk3BkLkxlszmNObqLTQQWZsH1Yo7ih03S++vjKnl2Ez+rTEp5sIfTaxLx75IOrhG9ViK89nuVOVX10tmLY/CBw1CmlYDT0jLfyIuylPj0I9LLe1jBntzJHJwyqARFnldLRQ1wVoYshfKZn23nmbUgfx/CGKDYMa98RrKSu7g6fVuUNTH2dQDF9gwtQVYq+oj0mxXHEK6P6lsh5DDdK1eBGRNPjrF8cdHkbxH8hgNTGZP43uSJMVpdsKt2+Mlf4qOPCZepX/3iuBlGO/X1rFt4n2uYVuiUcr//cUQJwmIQKBgQD9aHZ+rU+Au2wNGD9Y3RHTSWtfpyh7XUdqH9M9fo2hrET5iGe2cEVjScqTnWSuvZgm0uoogQE4sySJ95Vej9bbqtzcoMayEdPypX2c3mCWj8aOpkP5LbQ5ozWjCftdJHx4Mr1dRJyYl11kmxZ0fXBEjcdimjeB95qNG+ZWwGBpbQKBgQCLEnyoYBgIGc3AYdneO9ds8kGQvDg9OmibzVwxIcW0CY5cpBr2DYYLPPL45PYx7c4z9c5ujvleoUNCrmHRKDYfLlQZcfcKYZiacLoBVq2qG3balmXz4bvGmmtTJal134H2iZyT6g6vPBPhfvia/JYPW4nfHhIRL1i/GNEsG5NJzwKBgFU0jsNBWv4aBtbQVFx42MmGYkgjWIVCdATG9qwypUS7O+X7a4P4dBxSb3766SOoNES5nmpIFjUOZzbMvLiy/LeX1/OHS8gMHov0ASyByVBWv5ivZyOdrn/C1QIhFRfYcNGpneqCa3UTUB/H1eENXHZwhZ4t5IQa0SipPOXmgYRxAoGAXOuBNs7dv6vd+Uhn1UCOc1B51kasHNzFwDO6AWnbOquX3vYWvYfTo/aZMQtqI3oMaFM4Fk6Y900wL4FsAd+oKbU+o7GtqdYDrEzdOr+7UQkApJQQkp6kY9IRG4DydLtdjmfrojikjxjI/8ViPcu+R4yV1em1kJ3d4fCm8Z2LD6kCgYEArFPj91oVgPPCDR0FUPU7ICW9nf3kCnB8nFwIR2zrABB9G7Kfql6IT/vDl54eohrlt78wLmgFRSniAVNYlC9M7qGDNabmfH+JzdDX38j2Dlf00zy2Z7BGYPZwZ4mZyl6+ottKre1WXVv3fVVicr1dace3wKsID28ibmvKyVkXvIw=")
    private String privateKey;

    /**
     * 支付宝公钥,
     */
    @Value("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoJo02LVwFJ4A4zo5iLzBAGeB59PqmJV0Gi54s5eOvfANRm6GAKSyKvh8sDi+D1lE5ghEs2gF/uOa9ZNTD3MiINtO+dClc8XTwGDRxiMEPJfdkAG68ngPRwBCp8biECcAb6k9dzC8NFEYFRheVP+tuYiMetcptUfawo+bqabTk3lTKO5YQi4H729uK9yyVNXpSpembwewLFSWEy0k28EwBR1lqfC2Ma5+iYZVllHqmjRgW3X64nui73vqVsqMk6k/QeoAuf5wIIx3juvxqlUxm0SFlL0w1L6UelinnBDnlYdZ9a3Ds1Oz/diKYomfeM+9Xg0MEvxY/DAFLzeWhx6KkQIDAQAB")
    private String publicKey;

    /**
     * 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    @Value("http://2yf7p3.natappfree.cc/success")
    private String notifyUrl;

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
     */
    @Value("http://2yf7p3.natappfree.cc/userinfo")
    private String returnUrl;

    /**
     * 签名方式
     */
    @Value("RSA2")
    private String signType;

    /**
     * 字符编码格式
     */
    @Value("utf-8")
    private String charset;

    /**
     * 支付宝网关
     */
    @Value("https://openapi.alipaydev.com/gateway.do")
    private String gatewayUrl;

    /**
     * 支付宝网关
     */
    @Value("D://log")
    private String logPath;
}
