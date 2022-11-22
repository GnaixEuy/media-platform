package cn.gnaixeuy.mediaauth.controller;

import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/21
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
public class HelloController {

    @GetMapping(value = {"/hello"})
    public ResponseResult<String> hello() {
        return ResponseResult.success("Hello Media PlatForm");
    }

}
