package cn.gnaixeuy.mediauser.service;

import cn.gnaixeuy.mediauser.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/29
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void getUserDtoByUserId() {
        UserDto userDtoByUserId = this.userService.getUserDtoByUserId("31");
        System.out.println(userDtoByUserId);
    }
    
}