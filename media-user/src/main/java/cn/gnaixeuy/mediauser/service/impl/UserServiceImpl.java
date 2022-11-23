package cn.gnaixeuy.mediauser.service.impl;

import cn.gnaixeuy.mediacommon.enmus.ExceptionType;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.gnaixeuy.mediauser.entity.User;
import cn.gnaixeuy.mediauser.repository.UserRepository;
import cn.gnaixeuy.mediauser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUserPhone(username);
        if (user.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        log.info("从数据库加载用户信息: " + user);
        return user.get();
    }

    public User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("获取用户请求头信息： " + authentication);
        return (User) this.loadUserByUsername(authentication.getName());
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
