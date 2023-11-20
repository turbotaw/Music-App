package com.music.musicapp.util.data_access;

import com.music.musicapp.util.data_access.AppUser;
import com.music.musicapp.util.data_access.AppUser.DeviceType;
import com.music.musicapp.util.data_access.AppUser.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserTest {

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = new AppUser();
    }

    @Test
    void testGetUserId() {
        appUser.setUserId(123L);
        assertEquals(123L, appUser.getUserId());
    }

    @Test
    void testGetAndSetFirstName() {
        appUser.setFirstName("John");
        assertEquals("John", appUser.getFirstName());
    }

    @Test
    void testGetAndSetLastName() {
        appUser.setLastName("Doe");
        assertEquals("Doe", appUser.getLastName());
    }

    @Test
    void testGetAndSetFriendList() {
        List<Long> friends = new ArrayList<>();
        friends.add(1L);
        friends.add(2L);
        appUser.setFriendList(friends);
        assertEquals(friends, appUser.getFriendList());
    }

    @Test
    void testGetAndSetUserStatus() {
        appUser.setUserStatus(UserStatus.PAID);
        assertEquals(UserStatus.PAID, appUser.getUserStatus());
    }

    @Test
    void testGetAndSetDeviceTypes() {
        List<DeviceType> devices = new ArrayList<>();
        devices.add(DeviceType.IOS);
        devices.add(DeviceType.ANDROID);
        appUser.setDeviceTypes(devices);
        assertEquals(devices, appUser.getDeviceTypes());
    }

    @Test
    void testGetAndSetSignUpDate() {
        Date date = new Date();
        appUser.setSignUpDate(date);
        assertEquals(date, appUser.getSignUpDate());
    }

    @Test
    void testGetAndSetLastLogin() {
        Date date = new Date();
        appUser.setLastLogin(date);
        assertEquals(date, appUser.getLastLogin());
    }
}
