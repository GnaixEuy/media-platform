package cn.gnaixeuy.mediacomment.repository;

import cn.gnaixeuy.mediacomment.entity.FeedCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedCommentLikeRepository extends JpaRepository<FeedCommentLike, String> {
    @Query("select (count(f) > 0) from FeedCommentLike f where f.userId = ?1 and f.commentId = ?2")
    boolean existsByUserIdAndCommentId(String userId, String commentId);

    @Query("select count(f) from FeedCommentLike f where f.commentId = ?1")
    long countByCommentId(String commentId);
}