package cn.gnaixeuy.mediauser.repository;

import cn.gnaixeuy.mediauser.entity.Role;
import cn.gnaixeuy.mediauser.entity.User;
import cn.gnaixeuy.mediauser.enums.UserGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findAll() {
        List<User> all = this.userRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    void insertUser() {
        User user = new User();
        user.setUserNickname("test1111");
        user.setUserGender(UserGender.FEMALE);
        ArrayList<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId("1");
        roles.add(role);
        user.setRoles(roles);
        User save = this.userRepository.save(user);
        System.out.println(save);
    }

}