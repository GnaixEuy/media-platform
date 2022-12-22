package cn.gnaixeuy.medialike.service.impl;

import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.medialike.entity.Like;
import cn.gnaixeuy.medialike.repository.LikeRepository;
import cn.gnaixeuy.medialike.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Autowired
    public void setLikeRepository(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
}
