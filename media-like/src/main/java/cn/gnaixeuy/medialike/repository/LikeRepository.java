package cn.gnaixeuy.medialike.repository;

import cn.gnaixeuy.medialike.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {
    @Query("select count(l) from Like l where l.userId = ?1")
    long countByUserId(String userId);

    @Query("select (count(l) > 0) from Like l where l.userId = ?1 and l.feedId = ?2")
    boolean existsByUserIdAndFeedId(String userId, String feedId);

    @Query("select count(l) from Like l where l.feedId = ?1")
    long countByFeedId(String feedId);

    Optional<Like> findByUserIdAndFeedId(String userId, String feedId);

    List<Like> findByFeedId(String id);
}