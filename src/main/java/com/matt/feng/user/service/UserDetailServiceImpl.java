package com.matt.feng.user.service;

import com.matt.feng.user.accessingdatarest.UserDetailRepository;
import com.matt.feng.user.entity.UserDetail;
import com.matt.feng.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserDetailRepository userDetailRepository;


    @Override
    public UserDetail findById(Integer id) {
        return userDetailRepository.findById(id).orElseThrow(() -> new UserNotFoundException(("No UserDetail is found with id " + id)));
    }


    @Override
    public UserDetail updateUserDetail(UserDetail userDetail) {
        UserDetail user = findById(userDetail.getId());
        if (userDetail.getAddress() != null && user.getAddress() != null) {
            userDetail.getAddress().setId(user.getAddress().getId());
        }
        return userDetailRepository.save(userDetail);
    }
}
