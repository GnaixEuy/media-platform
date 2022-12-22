package cn.gnaixeuy.medialike.repository;

import cn.gnaixeuy.medialike.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

    Optional<Like> findByUserIdAndFeedId(String userId, String feedId);

}