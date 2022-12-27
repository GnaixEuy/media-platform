package cn.gnaixeuy.mediauser.client;

import cn.gnaixeuy.mediacommon.config.FeignConfig;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/27
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@FeignClient(value = "like-server", configuration = {FeignConfig.class})
public interface LikeFeignClient {

    @GetMapping(value = {"/like/getMyLikeFeedNumber/{id}"})
    ResponseResult<Long> getMyLikeFeedNumber(@PathVariable(value = "id") String id);

}
