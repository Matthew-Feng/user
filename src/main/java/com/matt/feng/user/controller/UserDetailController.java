package com.matt.feng.user.controller;

import com.matt.feng.user.entity.UserDetail;
import com.matt.feng.user.service.UserDetailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/userdetails")
@Slf4j
public class UserDetailController {
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/{id}")
    public UserDetail getUserDetailById(final @PathVariable(name = "id") Integer id) {
        return userDetailService.findById(id);
    }

    @PatchMapping("/{id}")
    public UserDetail updateUserDetail(final @PathVariable(name = "id") Integer id,
                                       final @RequestBody UserDetail userDetail) {
        userDetail.setId(id);
        return userDetailService.updateUserDetail(userDetail);
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String circularBreakExample() {
        throw new RuntimeException("Not available");
    }

    public String fallbackMethod() {
        return "Circular break is happening";
    }


}
