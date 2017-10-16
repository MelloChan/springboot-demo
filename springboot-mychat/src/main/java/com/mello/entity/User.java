package com.mello.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Random;

/**
 * 用户类
 * Created by MelloChan on 2017/10/15.
 */
@Data
@NoArgsConstructor
@ToString
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
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
