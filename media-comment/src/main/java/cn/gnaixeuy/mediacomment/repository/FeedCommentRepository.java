package cn.gnaixeuy.mediacomment.repository;

import cn.gnaixeuy.mediacomment.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedCommentRepository extends JpaRepository<FeedComment, String> {
    @Query("select count(f) from FeedComment f where f.feedId = ?1")
    long countByFeedId(String feedId);
    
}