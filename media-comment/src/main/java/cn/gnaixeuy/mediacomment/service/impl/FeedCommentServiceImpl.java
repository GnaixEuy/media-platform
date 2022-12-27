package cn.gnaixeuy.mediacomment.service.impl;


import cn.gnaixeuy.mediacomment.dto.AddCommentRequest;
import cn.gnaixeuy.mediacomment.dto.FeedCommentDto;
import cn.gnaixeuy.mediacomment.entity.FeedComment;
import cn.gnaixeuy.mediacomment.mapper.FeedCommentMapper;
import cn.gnaixeuy.mediacomment.repository.FeedCommentLikeRepository;
import cn.gnaixeuy.mediacomment.repository.FeedCommentRepository;
import cn.gnaixeuy.mediacomment.service.FeedCommentService;
import cn.gnaixeuy.mediacommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private FeedCommentMapper feedCommentMapper;
    private FeedCommentRepository feedCommentRepository;
    private FeedCommentLikeRepository feedCommentLikeRepository;


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

    @Override
    public List<FeedCommentDto> getCommentListByFeedId(String id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.feedCommentRepository.findByFeedId(id)
                .stream()
                .map(feedCommentMapper::entity2Dto)
                .peek(item -> item.setCommentLikeNumber(this.feedCommentLikeRepository.countByCommentId(item.getId())))
                .peek(item -> item.setCommentLike(this.feedCommentLikeRepository.existsByUserIdAndCommentId(currentUser.getId(), item.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean commentLike(String id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (this.feedCommentLikeRepository.existsByUserIdAndCommentId(currentUser.getId(), id)) {
            //TODO 点赞评论
        }
        return false;
    }


    @Autowired
    public void setFeedCommentMapper(FeedCommentMapper feedCommentMapper) {
        this.feedCommentMapper = feedCommentMapper;
    }

    @Autowired
    public void setFeedCommentRepository(FeedCommentRepository feedCommentRepository) {
        this.feedCommentRepository = feedCommentRepository;
    }

    @Autowired
    public void setFeedCommentLikeRepository(FeedCommentLikeRepository feedCommentLikeRepository) {
        this.feedCommentLikeRepository = feedCommentLikeRepository;
    }
}
