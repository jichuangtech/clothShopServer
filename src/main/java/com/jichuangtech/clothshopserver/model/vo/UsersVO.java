package com.jichuangtech.clothshopserver.model.vo;

/**
 * Created by yangjb on 2017/9/18.
 * helloWorld
 */
public class UsersVO {
    private Long userId;
    private String nickname;
    private String headPic;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
