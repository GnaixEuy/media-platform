package cn.gnaixeuy.mediafeed.service.impl;

import cn.gnaixeuy.mediacommon.entity.Feed;
import cn.gnaixeuy.mediacommon.entity.File;
import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.enums.ExceptionType;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediafeed.client.FileFeignClient;
import cn.gnaixeuy.mediafeed.client.LikeFeignClient;
import cn.gnaixeuy.mediafeed.dto.FeedDto;
import cn.gnaixeuy.mediafeed.dto.pojo.FeedListList;
import cn.gnaixeuy.mediafeed.dto.pojo.FeedListListContent;
import cn.gnaixeuy.mediafeed.dto.pojo.PublishFeedContentAttachments;
import cn.gnaixeuy.mediafeed.dto.request.PublishFeedRequest;
import cn.gnaixeuy.mediafeed.mapper.FeedMapper;
import cn.gnaixeuy.mediafeed.repository.FeedRepository;
import cn.gnaixeuy.mediafeed.service.FeedService;
import cn.gnaixeuy.mediafeed.vo.FeedListResponse;
import cn.gnaixeuy.mediafeed.vo.FeedVo;
import cn.gnaixeuy.mediafile.dto.FileDto;
import cn.gnaixeuy.mediafile.mapper.UserMapper;
import cn.gnaixeuy.mediafile.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private FeedMapper feedMapper;
    private FeedRepository feedRepository;
    private UserMapper userMapper;
    private FileFeignClient fileFeignClient;
    private LikeFeignClient likeFeignClient;
    private Map<String, StorageService> storageServices;


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
            ResponseResult<File> fileByKey = this.fileFeignClient.getFileByKey(item.getUrl());
            if (fileByKey.getCode() == 200) {
                feedContentList.add(fileByKey.getData());
                String[] split = fileByKey.getData().getName().split("\\.");
                ResponseResult<File> fileByName = this.fileFeignClient.getFileByName(split[0]);
                if (fileByName.getCode() == 200) {
                    feedContentList.add(fileByName.getData());
                }
            }
        });
        PublishFeedContentAttachments publishFeedContentAttachments = attachments.get(0);
        File file = feedContentList.get(0);
        Feed feed = new Feed();
        User currentUser = this.getCurrentUser();
        feed.setFile(file);
        //TODO coverFile 前端修改后填进去
        feed.setCover(feedContentList.get(1));
        feed.setSize(file.getSize());
        feed.setCreatedBy(currentUser);
        feed.setUpdatedBy(currentUser);
        feed.setDevice(publishFeedRequest.getDevice());
        feed.setBio(publishFeedRequest.getContent().getText());
        feed.setWidth(publishFeedContentAttachments.getWidth());
        feed.setHeight(publishFeedContentAttachments.getHeight());
        feed.setDuration(publishFeedContentAttachments.getDuration());
        feed.setType(publishFeedContentAttachments.getType());
//        publishFeedRequest.getDevice()
        feed.setDevice("iphone13");
//        System.out.println(save);
        return this.feedMapper.feedToFeedDto(this.feedRepository.save(feed));
    }

    @Override
    public FeedListResponse getHot(Integer cursor, Integer count) {
        Page<Feed> feedPage = this.feedRepository.findAll(
                PageRequest.of(cursor, count, Sort.by("createdDateTime").ascending()));
        return this.dealWithFeedList(feedPage);
    }

    @Override
    public FeedListResponse getUserWorksList(User createdBy, Integer cursor, Integer count) {
        Page<Feed> feedPage = this.feedRepository.findAllByCreatedBy(createdBy,
                PageRequest.of(cursor, count, Sort.by("createdDateTime").ascending()));
        return this.dealWithFeedList(feedPage);
    }

    @Override
    public Page<FeedDto> getVideoListPage(Pageable pageable) {
        Page<FeedDto> feedDtoPage = this.feedRepository.findAll(pageable).map(this.feedMapper::feedToFeedDto);
        feedDtoPage.getContent().forEach(feedDto -> {
            FileDto file = feedDto.getFile();
            FileDto cover = feedDto.getCover();
            cover.setUri(storageServices.get(cover.getStorage().name()).getFileUri(cover.getKey()));
            file.setUri(storageServices.get(file.getStorage().name()).getFileUri(file.getKey()));

        });
        return feedDtoPage;
    }

    @Override
    public boolean lockVideoById(String id) {
        Optional<Feed> byId = this.feedRepository.findById(id);
        if (byId.isEmpty()) {
            throw new BizException(ExceptionType.FEED_NOT_FOUND);
        }
        return 1 == this.feedRepository.updateLockedById(!byId.get().isLocked(), id);
    }

    @Override
    public boolean deleteVideoById(String id) {
        this.feedRepository.deleteById(id);
        return true;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    private FeedListResponse dealWithFeedList(Page<Feed> feedPage) {
        FeedListResponse feedListResponse = new FeedListResponse();
        List<FeedDto> contentDto = feedPage.getContent()
                .stream()
                .map(this.feedMapper::feedToFeedDto)
                .collect(Collectors.toList());
        List<FeedListList> feedList = new ArrayList<>(contentDto.size());
        contentDto.forEach(item -> {
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
            ResponseResult<Long> feedLikeNumByFeedId = this.likeFeignClient.getFeedLikeNumByFeedId(feedInfo.getId());
            if (feedLikeNumByFeedId.getCode() == 200) {
                feedInfo.setLikeCount(feedLikeNumByFeedId.getData());
            }
            ResponseResult<Boolean> feedIsLikeByFeedIdAndUserId = this.likeFeignClient.getFeedIsLikeByFeedIdAndUserId(item.getCreatedBy().getId(), feedInfo.getId());
            if (feedIsLikeByFeedIdAndUserId.getCode() == 200) {
                feedInfo.setIsLike(feedIsLikeByFeedIdAndUserId.getData());
            }
            System.out.println("--------------------------------");
            System.out.println(feedInfo.getIsLike());
            System.out.println("--------------------------------");
            feedList.add(feedInfo);
        });
        feedListResponse.setList(feedList);
        feedListResponse.setCount(feedPage.getSize());
        feedListResponse.setCursor(feedPage.getNumber());
        feedListResponse.setHasMore(feedPage.getNumber() + 1 <= feedPage.getTotalPages());
        return feedListResponse;
    }


    @Autowired
    public void setFeedRepository(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @Autowired
    public void setFileFeignClient(FileFeignClient fileFeignClient) {
        this.fileFeignClient = fileFeignClient;
    }

    @Autowired
    public void setFeedMapper(FeedMapper feedMapper) {
        this.feedMapper = feedMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setLikeFeignClient(LikeFeignClient likeFeignClient) {
        this.likeFeignClient = likeFeignClient;
    }

    @Autowired
    public void setStorageServices(Map<String, StorageService> storageServices) {
        this.storageServices = storageServices;
    }
}
