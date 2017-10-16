package com.mello.service;

import com.mello.entity.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户服务类
 * Created by MelloChan on 2017/10/15.
 */
@Service
public class UserService {
    private final ConcurrentHashMap<String,User>users;

    public UserService(){
        users=new ConcurrentHashMap<>();
    }
//    public boolean addUser(User user){
//            boolean isExist=users.containsKey(user.getNickname());
//            if(isExist){
//                return false;
//            }
//            users.put();
//            return true;
//    }
}
