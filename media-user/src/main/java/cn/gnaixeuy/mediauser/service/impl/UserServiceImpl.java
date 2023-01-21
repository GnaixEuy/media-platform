package cn.gnaixeuy.mediauser.service.impl;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.enums.ExceptionType;
import cn.gnaixeuy.mediacommon.enums.UserGender;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.gnaixeuy.mediauser.dto.UserDto;
import cn.gnaixeuy.mediauser.dto.request.FollowRequest;
import cn.gnaixeuy.mediauser.dto.request.UserInfoUpdateRequest;
import cn.gnaixeuy.mediauser.entity.UserFollow;
import cn.gnaixeuy.mediauser.enums.FollowType;
import cn.gnaixeuy.mediauser.mapper.UserMapper;
import cn.gnaixeuy.mediauser.repository.UserFollowRepository;
import cn.gnaixeuy.mediauser.repository.UserRepository;
import cn.gnaixeuy.mediauser.service.UserService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private UserFollowRepository userFollowRepository;


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
            String substring = null;
            try {
                substring = portrait.substring(0, portrait.indexOf("?"));
                user.setPortrait(substring);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StrUtil.isNotEmpty(profession)) {
            user.setProfession(profession);
        }
        User save = this.userRepository.save(user);
        return this.userMapper.entity2Dto(save);
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(this.userMapper::entity2Dto);
    }

    @Override
    public UserDto lockUserById(String id) {
        Optional<User> byId = this.userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        User user = byId.get();
        int i = this.userRepository.updateLockedById(!user.getLocked(), user.getId());
        if (i != 1) {
            throw new BizException(ExceptionType.USER_UPDATE_EXCEPTION);
        }
        return this.userMapper.entity2Dto(this.userRepository.findById(id).get());
    }

    @Override
    public Boolean deleteUserById(String id) {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<UserDto> searchByConditions(String type, String condition) {
        List<User> list;
        switch (type) {
            case "id":
                list = this.userRepository.findByIdContaining(condition);
                break;
            case "name":
                System.out.println("匹配名字");
                list = this.userRepository.findByUserNicknameContaining(condition);
                break;
            case "phone":
                list = this.userRepository.findByUserPhoneContaining(condition);
                break;
            default:
                list = this.userRepository.findByIdContainingOrUserNicknameContainingOrUserPhoneContaining(condition, condition, condition);
        }
        return list.stream().map(this.userMapper::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public boolean follow(FollowRequest followRequest) {
        System.out.println(followRequest);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserFollow userFollow = new UserFollow();
        userFollow.setUserId(currentUser.getId());
        userFollow.setFollowId(followRequest.getRelationUid());
        boolean existsByUserIdAndFollowId = this.userFollowRepository.existsByUserIdAndFollowId(userFollow.getUserId(), userFollow.getFollowId());
        if (followRequest.getActionType() == FollowType.FOLLOW) {
            if (existsByUserIdAndFollowId) {
                return false;
            }
            this.userFollowRepository.save(userFollow);
        } else {
            if (!existsByUserIdAndFollowId) {
                return false;
            }
            return 1 == this.userFollowRepository.deleteByFollowIdAndUserId(followRequest.getRelationUid(), currentUser.getId());
        }
        return true;
    }

    @Override
    public Boolean isFollowRelation(String userId, String followId) {
        return this.userFollowRepository.existsByUserIdAndFollowId(userId, followId);
    }

    @Override
    public Long getFollowerNumber(String id) {
        return this.userFollowRepository.countByFollowId(id);
    }

    @Override
    public Long getFollowingNumber(String id) {
        return this.userFollowRepository.countByUserId(id);
    }

    @Override
    public List<User> getFollowList(String id) {
        List<UserFollow> byUserId = this.userFollowRepository.findByUserId(id);
        List<User> userList = new ArrayList<>(byUserId.size());
        byUserId.forEach(item -> {
            userList.add(this.userRepository.getOne(item.getFollowId()));
        });
        return userList;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserFollowRepository(UserFollowRepository userFollowRepository) {
        this.userFollowRepository = userFollowRepository;
    }
}
