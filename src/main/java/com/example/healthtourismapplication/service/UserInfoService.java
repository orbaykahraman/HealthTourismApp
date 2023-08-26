package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.UserInfo;
import com.example.healthtourismapplication.database.repository.UserInfoRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserInfo userInfo) {

        logger.info("add user started");

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        if(StringUtils.isEmpty(userInfo.getRoles())) {
            userInfo.setRoles("ROLE_USER");
        }
        UserInfo savedUser = userInfoRepository.save(userInfo);
        logger.info("add user ended");
    }
}
