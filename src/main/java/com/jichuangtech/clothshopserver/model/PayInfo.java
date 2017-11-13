package com.jichuangtech.clothshopserver.model;

/**
 * Created by Bingo on 2017/11/12.
 */
public class PayInfo {
    public String nonceStr;
    public String packageStr;
    public String timeStamp;
    public String paySign;

    @Override
    public String toString() {
        return "PayInfo{" +
                "nonceStr='" + nonceStr + '\'' +
                ", packageStr='" + packageStr + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}
