package org.jeffrey.oauth.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey.Liu
 * @create 2017-11-30 17:33
 **/
public class UserOverviewDto implements Serializable {

    private static final long serialVersionUID = 2023379587030489248L;

    private String userName;
    private List<UserDto> userDtos = new ArrayList<>();

    public int getSize(){
        return userDtos.size();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }
}
