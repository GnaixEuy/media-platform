package cn.gnaixeuy.mediauser.repository;

import cn.gnaixeuy.mediauser.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, String> {
    @Query("select u from UserFollow u where u.userId = ?1")
    List<UserFollow> findByUserId(String userId);

    @Query("select count(u) from UserFollow u where u.userId = ?1")
    long countByUserId(String userId);

    @Query("select count(u) from UserFollow u where u.followId = ?1")
    long countByFollowId(String followId);

    @Transactional
    @Modifying
    @Query("delete from UserFollow u where u.followId = ?1 and u.userId = ?2")
    int deleteByFollowIdAndUserId(String followId, String userId);

    @Query("select (count(u) > 0) from UserFollow u where u.userId = ?1 and u.followId = ?2")
    boolean existsByUserIdAndFollowId(String userId, String followId);
}