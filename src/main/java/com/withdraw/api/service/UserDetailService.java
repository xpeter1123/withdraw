package com.withdraw.api.service;

import com.withdraw.domain.entity.UserDetailEntity;

import java.util.Optional;

public interface UserDetailService {
     Optional<UserDetailEntity> findOneAndLock(Long id);

}
