package org.jeffrey.oauth.dto;

import org.jeffrey.oauth.model.User;
import org.jeffrey.oauth.util.Privilege;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工信息
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 16:21
 **/
public class UserJsonDto implements Serializable {
    private String guid;
    private boolean archived;
    private String userName;
    private  String phone;
    private String email;

    private List<String> privileges = new ArrayList<String>();
    public UserJsonDto(){}
    public UserJsonDto(User user){
        this.guid = user.getGuid();
        this.archived = user.getArchived();
        this.phone = user.getPhone();
        this.email = user.getEmail();

        final List<Privilege> privilegeList = user.getPrivileges();
        for (Privilege privilege:privilegeList){
            this.privileges.add(privilege.name());
        }
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
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

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}
