package cn.gnaixeuy.mediagateway.repository;

import cn.gnaixeuy.mediagateway.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUserPhone(String phone);

}