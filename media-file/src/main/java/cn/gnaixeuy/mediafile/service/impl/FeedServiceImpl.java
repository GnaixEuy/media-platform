package cn.gnaixeuy.mediafile.service.impl;

import cn.gnaixeuy.mediafile.dto.FeedDto;
import cn.gnaixeuy.mediafile.dto.pojo.FeedListList;
import cn.gnaixeuy.mediafile.dto.pojo.FeedListListContent;
import cn.gnaixeuy.mediafile.dto.pojo.PublishFeedContentAttachments;
import cn.gnaixeuy.mediafile.dto.request.PublishFeedRequest;
import cn.gnaixeuy.mediafile.entity.Feed;
import cn.gnaixeuy.mediafile.entity.File;
import cn.gnaixeuy.mediafile.entity.User;
import cn.gnaixeuy.mediafile.mapper.FeedMapper;
import cn.gnaixeuy.mediafile.mapper.FileMapper;
import cn.gnaixeuy.mediafile.mapper.UserMapper;
import cn.gnaixeuy.mediafile.repository.FeedRepository;
import cn.gnaixeuy.mediafile.repository.FileRepository;
import cn.gnaixeuy.mediafile.service.FeedService;
import cn.gnaixeuy.mediafile.vo.FeedListResponse;
import cn.gnaixeuy.mediafile.vo.FeedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/12/2
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class FeedServiceImpl implements FeedService {

    private FeedRepository feedRepository;
    private FileRepository fileRepository;
    private FeedMapper feedMapper;
    private UserMapper userMapper;
    private FileMapper fileMapper;


    /**
     * 发布视屏请求
     *
     * @param publishFeedRequest 视屏请求
     * @return 待定
     */
    @Override
    @Transactional
    public FeedDto publicVideoFeed(PublishFeedRequest publishFeedRequest) {
        List<PublishFeedContentAttachments> attachments = publishFeedRequest.getContent().getAttachments();
        //TODO 照片模式等待 2.0版本
        List<File> feedContentList = new ArrayList<>();
        attachments.forEach(item -> {
            Optional<File> byKey = this.fileRepository.findByKey(item.getUrl());
            byKey.ifPresent(feedContentList::add);
        });
        PublishFeedContentAttachments publishFeedContentAttachments = attachments.get(0);
        File file = feedContentList.get(0);
        Feed feed = new Feed();
        User currentUser = this.getCurrentUser();
        //TODO
        feed.setFile(file);
        feed.setCover(file);
        feed.setSize(file.getSize());
        feed.setCreatedBy(currentUser);
        feed.setUpdatedBy(currentUser);
        feed.setDevice(publishFeedRequest.getDevice());
        feed.setBio(publishFeedRequest.getContent().getText());
        feed.setWidth(publishFeedContentAttachments.getWidth());
        feed.setHeight(publishFeedContentAttachments.getHeight());
        feed.setDuration(publishFeedContentAttachments.getDuration());
        feed.setType(publishFeedContentAttachments.getType());
        feed.setDevice(publishFeedRequest.getDevice());
//        System.out.println(save);
        return this.feedMapper.feedToFeedDto(this.feedRepository.save(feed));
    }

    @Override
    public FeedListResponse getHot(Integer cursor, Integer count) {
        Page<Feed> feedPage = this.feedRepository.findAll(
                PageRequest.of(cursor, count, Sort.by("createdDateTime").ascending()));
        FeedListResponse feedListResponse = new FeedListResponse();
        List<FeedDto> contentDto = feedPage.getContent()
                .stream()
                .map(this.feedMapper::feedToFeedDto)
                .collect(Collectors.toList());

        List<FeedListList> feedList = new ArrayList<>(contentDto.size());
        contentDto.forEach(item -> {
            System.out.println(item);
            FeedListList feedInfo = new FeedListList();
            feedInfo.setId(item.getId());
            feedInfo.setType(item.getType().toString());
            feedInfo.setDevice(item.getDevice());
            String prefix = "https://media-1301661174.cos.ap-shanghai.myqcloud.com";
            feedInfo.setContent(new FeedListListContent(item.getBio(), new ArrayList<>() {{
                FeedVo feedVo = new FeedVo(
                        item.getType(),
                        prefix + item.getFile().getKey(),
                        prefix + item.getCover().getKey(),
                        prefix + item.getCover().getKey(),
                        item.getDuration(),
                        item.getWidth(),
                        item.getHeight()
                );
                this.add(feedVo);
            }}));
            feedInfo.setCreatedDateTime(item.getCreatedDateTime());
            feedInfo.setUser(this.userMapper.dto2Vo(item.getCreatedBy()));
            feedList.add(feedInfo);
        });
        feedListResponse.setList(feedList);
        return feedListResponse;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @Autowired
    public void setFeedRepository(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @Autowired
    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Autowired
    public void setFeedMapper(FeedMapper feedMapper) {
        this.feedMapper = feedMapper;
    }

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
