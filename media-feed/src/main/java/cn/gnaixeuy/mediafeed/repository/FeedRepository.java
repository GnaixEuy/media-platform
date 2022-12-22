package cn.gnaixeuy.mediafeed.repository;

import cn.gnaixeuy.mediacommon.entity.Feed;
import cn.gnaixeuy.mediacommon.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
    @Transactional
    @Modifying
    @Query("update Feed f set f.locked = ?1 where f.id = ?2")
    int updateLockedById(boolean locked, String id);

    Page<Feed> findAllByCreatedBy(User createdBy, Pageable pageable);

}
