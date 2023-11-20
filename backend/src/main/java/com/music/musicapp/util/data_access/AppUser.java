package com.music.musicapp.util.data_access;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "appuser", schema = "public")
public class AppUser {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @ElementCollection
    @CollectionTable(name = "friend_list")
    private List<Long> friendList;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @ElementCollection
    @CollectionTable(name = "device_types")
    @Enumerated(EnumType.STRING)
    private List<DeviceType> deviceTypes;

    @Column(name = "sign_up_date")
    private Date signUpDate;

    @Column(name = "last_login")
    private Date lastLogin;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Long> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Long> friendList) {
        this.friendList = friendList;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<DeviceType> getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(List<DeviceType> deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    // Enums for user_status and device_type
    public enum UserStatus {
        FREE, PAID, TRIAL
    }

    public enum DeviceType {
        IOS, ANDROID, DESKTOP
    }
}