package cn.gnaixeuy.mediauser.service.impl;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.enums.ExceptionType;
import cn.gnaixeuy.mediacommon.enums.UserGender;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.gnaixeuy.mediauser.dto.UserDto;
import cn.gnaixeuy.mediauser.dto.request.UserInfoUpdateRequest;
import cn.gnaixeuy.mediauser.mapper.UserMapper;
import cn.gnaixeuy.mediauser.repository.UserRepository;
import cn.gnaixeuy.mediauser.service.UserService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    /**
     * 通过userid 获取userDto信息
     *
     * @param id userId
     * @return userDto
     */
    @Override
    public UserDto getUserDtoByUserId(String id) {
        Optional<User> byId = this.userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return this.userMapper.entity2Dto(byId.get());
    }

    @Override
    public User getUserInfoByUserId(String id) {
        Optional<User> byId = this.userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return byId.get();
    }

    @Override
    public UserDto updateUserInfoById(String id, UserInfoUpdateRequest userInfoUpdateRequest) {
        Optional<User> byId = this.userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        User user = byId.get();
        String bio = userInfoUpdateRequest.getBio();
        Date birth = userInfoUpdateRequest.getBirth();
        String city = userInfoUpdateRequest.getCity();
        String nickname = userInfoUpdateRequest.getNickname();
        UserGender gender = userInfoUpdateRequest.getGender();
        String portrait = userInfoUpdateRequest.getPortrait();
        String profession = userInfoUpdateRequest.getProfession();
        if (StrUtil.isNotEmpty(bio)) {
            user.setBio(bio);
        }
        if (birth != null) {
            user.setUserBirthday(birth);
        }
        if (StrUtil.isNotEmpty(city)) {
            user.setUserCity(city);
        }
        if (StrUtil.isNotEmpty(nickname)) {
            user.setUserNickname(nickname);
        }
        if (gender != null) {
            user.setUserGender(gender);
        }
        if (StrUtil.isNotEmpty(portrait)) {
            user.setPortrait(portrait);
        }
        if (StrUtil.isNotEmpty(profession)) {
            user.setProfession(profession);
        }
        User save = this.userRepository.save(user);
        return this.userMapper.entity2Dto(save);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
