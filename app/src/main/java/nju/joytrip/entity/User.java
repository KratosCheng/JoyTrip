package nju.joytrip.entity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {
    private String nickName;
    private String userPic;

    public String getNickname(){
        return nickName;
    }

    public void setNickname(String s){
        this.nickName = s;
    }

    public String getUserPic(){
        return userPic;
    }

    public void setUserPic(String url){
        this.userPic = url;
    }
}
