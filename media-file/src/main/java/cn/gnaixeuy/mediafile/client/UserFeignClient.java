package cn.gnaixeuy.mediafile.client;

import cn.gnaixeuy.mediacommon.config.FeignConfig;
import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/30
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@FeignClient(value = "user-server", configuration = {FeignConfig.class})
public interface UserFeignClient {

    @GetMapping(value = {"/user/info/{id}"})
    ResponseResult<UserVo> getCurrentUserInfo(@PathVariable(value = "id") String id);

    @GetMapping(value = {"/user/info/entity/{id}"})
    ResponseResult<User> getUserInfoById(@PathVariable(value = "id") String id);

}
