package com.example.healthtourismapplication.database.repository;

import com.example.healthtourismapplication.database.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String username);
    Optional<UserInfo> getUserInfoByName(String username);
    Optional<UserInfo> findByEmail(String email);
}
