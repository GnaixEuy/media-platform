package cn.gnaixeuy.mediacomment.service.impl;

import cn.gnaixeuy.mediacomment.dto.AddCommentRequest;
import cn.gnaixeuy.mediacomment.entity.FeedComment;
import cn.gnaixeuy.mediacomment.repository.FeedCommentRepository;
import cn.gnaixeuy.mediacomment.service.FeedCommentService;
import cn.gnaixeuy.mediacommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/26
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class FeedCommentServiceImpl implements FeedCommentService {

    private FeedCommentRepository feedCommentRepository;


    @Autowired
    public void setFeedCommentRepository(FeedCommentRepository feedCommentRepository) {
        this.feedCommentRepository = feedCommentRepository;
    }

    @Override
    public boolean addComment(AddCommentRequest addCommentRequest) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FeedComment feedComment = new FeedComment(
                addCommentRequest.getId(),
                //TODO
                null,
                addCommentRequest.getDetails(),
                currentUser
        );
        FeedComment save = this.feedCommentRepository.save(feedComment);
        return save.getId() != null;
    }

    @Override
    public Long getCommentNumberByFeedId(String feedId) {
        return this.feedCommentRepository.countByFeedId(feedId);
    }
    
}
