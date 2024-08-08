package org.application.openschoolhomeworkaop;

import org.application.openschoolhomeworkaop.exceptions.ElementNotFoundException;
import org.application.openschoolhomeworkaop.models.User;
import org.application.openschoolhomeworkaop.repositories.UserRepository;
import org.application.openschoolhomeworkaop.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    private final Long ID = 1L;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User();
        user.setId(ID);
        user.setName("Ivan");
        user.setEmail("test@example.ru");

    }

    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject(){
        //given
        given(userRepository.findById(ID)).willReturn(Optional.of(user));
        //when
        User actualUser = userService.getUserById(ID);
        //then
        assertEquals(user,actualUser);
    }

    @Test
    public void givenNotExistingUserId_whenGetUserById_thenThrowException(){
        given(userRepository.findById(ID)).willReturn(Optional.empty());

        assertThrows(ElementNotFoundException.class, () -> userService.getUserById(ID));

        verify(userRepository).findById(anyLong());
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenReturnUpdatedUser(){
        given(userRepository.save(user)).willReturn(user);

        userService.updateUserById(999L,user);

        assertEquals(999L, user.getId());
        verify(userRepository).save(any(User.class));
    }
}
