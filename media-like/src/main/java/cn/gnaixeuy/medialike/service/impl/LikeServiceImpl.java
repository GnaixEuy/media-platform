package cn.gnaixeuy.medialike.service.impl;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediacommon.vo.user.UserVo;
import cn.gnaixeuy.medialike.client.UserFeignClient;
import cn.gnaixeuy.medialike.entity.Like;
import cn.gnaixeuy.medialike.repository.LikeRepository;
import cn.gnaixeuy.medialike.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/22
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;
    private UserFeignClient userFeignClient;

    @Override
    public boolean likeById(String id, User user, boolean isLike) {
        Optional<Like> byUserIdAndFeedId = this.likeRepository.findByUserIdAndFeedId(user.getId(), id);
        if (isLike) {
            if (byUserIdAndFeedId.isPresent()) {
                return true;
            }
            this.likeRepository.save(new Like(user.getId(), id));
        } else {
            if (byUserIdAndFeedId.isEmpty()) {
                return true;
            } else {
                this.likeRepository.deleteById(byUserIdAndFeedId.get().getId());
            }
        }
        return true;
    }

    @Override
    public Long getFeedLikeNum(String id) {
        return this.likeRepository.countByFeedId(id);
    }

    @Override
    public Boolean getFeedIsLikeByFeedIdAndUserId(String userId, String feedId) {
        return this.likeRepository.existsByUserIdAndFeedId(userId, feedId);
    }

    @Override
    public List<UserVo> getLikeUserListByFeedId(String id) {
        List<Like> byFeedId = this.likeRepository.findByFeedId(id);
        List<UserVo> userVoArrayList = new ArrayList<>(byFeedId.size());
        byFeedId.forEach(item -> {
            ResponseResult<UserVo> userInfoById = this.userFeignClient.getUserInfoById(item.getUserId());
            if (userInfoById.getCode() == 200) {
                UserVo data = userInfoById.getData();
                //暂时不定义返回response，借用userVo的createdDateTime
                data.setCreatedDateTime(item.getCreatedDateTime());
                userVoArrayList.add(data);
            }
        });
        return userVoArrayList;
    }


    @Autowired
    public void setLikeRepository(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Autowired
    public void setUserFeignClient(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }
}
