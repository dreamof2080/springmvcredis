package org.jeffrey.oauth.model;


import org.jeffrey.oauth.base.AbstractDomain;
import org.jeffrey.oauth.util.Privilege;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定义用户
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 16:33
 **/
public class User extends AbstractDomain{

    private static final long serialVersionUID = -2921689304753120556L;

    private String userName;
    private String password;
    private String phone;
    private String email;
    /** Default user is initial when create database,do not delete **/
    private boolean defaultUser = false;
    private Date lastLoginTime;
    private List<Privilege> privileges = new ArrayList<>();

    public User(){}

    public User(String userName,String password,String phone,String email){
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{userName='").append(userName).append("\'");
        stringBuilder.append(",phone='").append(phone).append("\'");
        stringBuilder.append(",id='").append(id).append("\'");
        stringBuilder.append(",guid='").append(guid).append("\'");
        stringBuilder.append(",defaultUser='").append(defaultUser).append("\'");
        stringBuilder.append(",email='").append(email).append("\'");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public User email(String email){
        this.email = email;
        return this;
    }

    public User phone(String phone){
        this.phone = phone;
        return this;
    }

    public User userName(String userName){
        this.userName = userName;
        return this;
    }

    public User lastLoginTime(Date lastLoginTime){
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public User createTime(LocalDateTime createTime){
        this.createTime = createTime;
        return this;
    }

    public User password(String password){
        this.password = password;
        return this;
    }
}
