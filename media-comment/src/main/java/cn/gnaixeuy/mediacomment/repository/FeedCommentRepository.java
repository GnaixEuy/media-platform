package cn.gnaixeuy.mediacomment.repository;

import cn.gnaixeuy.mediacomment.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedCommentRepository extends JpaRepository<FeedComment, String> {

    List<FeedComment> findByFeedId(String feedId);

    @Query("select count(f) from FeedComment f where f.feedId = ?1")
    long countByFeedId(String feedId);

}