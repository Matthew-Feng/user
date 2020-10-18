package com.matt.feng.user.service;

import com.matt.feng.user.entity.UserDetail;

public interface UserDetailService {
    UserDetail findById(Integer id);

    UserDetail updateUserDetail(UserDetail userDetail);
}
