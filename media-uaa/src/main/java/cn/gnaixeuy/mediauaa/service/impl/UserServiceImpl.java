package cn.gnaixeuy.mediauaa.service.impl;

import cn.gnaixeuy.mediacommon.dto.UserDto;
import cn.gnaixeuy.mediacommon.enums.ExceptionType;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.gnaixeuy.mediauaa.dto.RegisterByPhoneRequest;
import cn.gnaixeuy.mediauaa.entity.Role;
import cn.gnaixeuy.mediauaa.entity.User;
import cn.gnaixeuy.mediauaa.enums.UserGender;
import cn.gnaixeuy.mediauaa.mapper.UserMapper;
import cn.gnaixeuy.mediauaa.repository.UserRepository;
import cn.gnaixeuy.mediauaa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUserPhone(username);
        if (user.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    private User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("获取用户请求头信息： " + authentication);
        return (User) this.loadUserByUsername(authentication.getName());
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 获取去当前登录用户的信息
     *
     * @return user dto
     */
    @Override
    public UserDto getCurrentUserInfo() {
        return this.userMapper.entity2Dto(this.getCurrentUserEntity());
    }

    @Override
    public UserDto registerByPhone(RegisterByPhoneRequest registerByPhoneRequest) {
        System.out.println(registerByPhoneRequest);
        //TODO 验证码机制
        //TODO 默认头像
        User user = new User();
        user.setUserPhone(registerByPhoneRequest.getPhone());
        user.setUserNickname("TikTok用户_" + registerByPhoneRequest.getPhone().substring(registerByPhoneRequest.getPhone().length() - 4));
        List<Role> roleList = new ArrayList<>() {{
            Role role = new Role();
            role.setId("1");
            this.add(role);
        }};
        user.setRoles(roleList);
        user.setUserGender(UserGender.UNKNOWN);
        user.setPassword(this.passwordEncoder.encode(registerByPhoneRequest.getPassword()));
        return this.userMapper.entity2Dto(this.userRepository.save(user));
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
