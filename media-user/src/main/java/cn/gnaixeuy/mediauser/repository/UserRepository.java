package cn.gnaixeuy.mediauser.repository;

import cn.gnaixeuy.mediacommon.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    @Modifying
    @Query("update User u set u.locked = ?1 where u.id = ?2")
    int updateLockedById(Boolean locked, String id);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUserPhone(String phone);

    List<User> findByIdContaining(String id);

    List<User> findByUserNicknameContaining(String nickname);

    List<User> findByUserPhoneContaining(String phoneNumber);

    List<User> findByIdContainingOrUserNicknameContainingOrUserPhoneContaining(String id, String userNickname, String userPhone);
}