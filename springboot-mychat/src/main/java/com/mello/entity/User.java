package com.mello.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Random;

/**
 * 用户类
 * Created by MelloChan on 2017/10/15.
 */
@Data
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private Date joinTime;

    public User(String username,String password,String nickname){
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.avatar="/image/avatar/avatar"+new Random().nextInt(10)+".jpg";
        this.joinTime=new Date();
    }
}
