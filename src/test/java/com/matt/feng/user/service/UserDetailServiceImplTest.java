package com.matt.feng.user.service;


import com.matt.feng.user.accessingdatarest.UserDetailRepository;
import com.matt.feng.user.entity.UserDetail;
import com.matt.feng.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceImplTest {
    @Mock
    private UserDetailRepository userDetailRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();

    private UserDetail userDetail;

    @BeforeEach
    void setUp() {
        userDetail = UserDetail.builder().firstName("Matthew").build();
    }

    @Test
    void shouldReturnAnUserDetail() {
        Mockito.when(userDetailRepository.findById(anyInt())).thenReturn(Optional.of(userDetail));
        UserDetail result = userDetailService.findById(1);
        assertNotNull(result);
        assertEquals("Matthew", userDetail.getFirstName());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenNoEntityInDB() {
        Mockito.when(userDetailRepository.findById(anyInt())).thenReturn(Optional.empty());
        UserNotFoundException thrown = assertThrows(
                UserNotFoundException.class,
                () -> userDetailService.findById(100),
                "Expected findById() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("No UserDetail is found with id "));
    }
}
