package org.jeffrey.oauth.dto;

import org.jeffrey.oauth.model.User;
import org.jeffrey.oauth.util.Privilege;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey.Liu
 * @create 2017-11-30 17:49
 **/
public class UserDto implements Serializable{
    private static final long serialVersionUID = -2502329463915439215L;

    private String guid;
    private String userName;
    private String phone;
    private String email;
    private String createTime;
    private List<Privilege> privileges = new ArrayList<>();
    public UserDto(){}
    public UserDto(User user){
        this.guid = user.getGuid();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.privileges = user.getPrivileges();
        this.createTime = user.getCreateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public static List<UserDto> toDtos(List<User> users){
        List<UserDto> dtos = new ArrayList<>(users.size());
        for (User user:users){
            dtos.add(new UserDto(user));
        }
        return dtos;
    }
}
