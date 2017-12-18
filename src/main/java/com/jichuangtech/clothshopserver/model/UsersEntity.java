package com.jichuangtech.clothshopserver.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by yangjb on 2017/9/18.
 * helloWorld
 */
@Entity
@Table(name = "users", schema = "clothshop", catalog = "")
public class UsersEntity {
    private Long userId;
    private String email;
    private String password;
    private String paypwd;
    private byte sex;
    private int birthday;
    private BigDecimal userMoney;
    private BigDecimal frozenMoney;
    private BigDecimal distributMoney;
    private int payPoints;
    private int regTime;
    private int lastLogin;
    private String lastIp;
    private String qq;
    private String mobile;
    private byte mobileValidated;
    private String oauth;
    private String openid;
    private String unionid;
    private String headPic;
    private Integer province;
    private Integer city;
    private Integer district;
    private byte emailValidated;
    private String nickname;
    private Byte level;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private Byte isLock;
    private Byte isDistribut;
    private Integer firstLeader;
    private Integer secondLeader;
    private Integer thirdLeader;
    private Integer loginCount;
    private String token;
    private Timestamp craetedAt;
    private Timestamp updatedAt;
    private Timestamp lastLoginTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 60)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "paypwd", nullable = true, length = 32)
    public String getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "user_money", nullable = true, precision = 2)
    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    @Basic
    @Column(name = "frozen_money", nullable = true, precision = 2)
    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    @Basic
    @Column(name = "distribut_money", nullable = true, precision = 2)
    public BigDecimal getDistributMoney() {
        return distributMoney;
    }

    public void setDistributMoney(BigDecimal distributMoney) {
        this.distributMoney = distributMoney;
    }

    @Basic
    @Column(name = "pay_points", nullable = true)
    public int getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(int payPoints) {
        this.payPoints = payPoints;
    }

    @Basic
    @Column(name = "reg_time", nullable = true)
    public int getRegTime() {
        return regTime;
    }

    public void setRegTime(int regTime) {
        this.regTime = regTime;
    }

    @Basic
    @Column(name = "last_login", nullable = true)
    public int getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(int lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "last_ip", nullable = true, length = 15)
    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 20)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "mobile_validated", nullable = true)
    public byte getMobileValidated() {
        return mobileValidated;
    }

    public void setMobileValidated(byte mobileValidated) {
        this.mobileValidated = mobileValidated;
    }

    @Basic
    @Column(name = "oauth", nullable = true, length = 10)
    public String getOauth() {
        return oauth;
    }

    public void setOauth(String oauth) {
        this.oauth = oauth;
    }

    @Basic
    @Column(name = "openid", nullable = true, length = 100)
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "unionid", nullable = true, length = 100)
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Basic
    @Column(name = "head_pic", nullable = true, length = 255)
    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    @Basic
    @Column(name = "province", nullable = true)
    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city", nullable = true)
    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    @Basic
    @Column(name = "district", nullable = true)
    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    @Basic
    @Column(name = "email_validated", nullable = true)
    public byte getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(byte emailValidated) {
        this.emailValidated = emailValidated;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 50)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 2)
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "total_amount", nullable = true, precision = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "is_lock", nullable = true)
    public Byte getIsLock() {
        return isLock;
    }

    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
    }

    @Basic
    @Column(name = "is_distribut", nullable = true)
    public Byte getIsDistribut() {
        return isDistribut;
    }

    public void setIsDistribut(Byte isDistribut) {
        this.isDistribut = isDistribut;
    }

    @Basic
    @Column(name = "first_leader", nullable = true)
    public Integer getFirstLeader() {
        return firstLeader;
    }

    public void setFirstLeader(Integer firstLeader) {
        this.firstLeader = firstLeader;
    }

    @Basic
    @Column(name = "second_leader", nullable = true)
    public Integer getSecondLeader() {
        return secondLeader;
    }

    public void setSecondLeader(Integer secondLeader) {
        this.secondLeader = secondLeader;
    }

    @Basic
    @Column(name = "third_leader", nullable = true)
    public Integer getThirdLeader() {
        return thirdLeader;
    }

    public void setThirdLeader(Integer thirdLeader) {
        this.thirdLeader = thirdLeader;
    }

    @Basic
    @Column(name = "login_count", nullable = true)
    public Integer getloginCount() {
        return loginCount;
    }

    public void setloginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 64)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "craeted_at", nullable = true)
    public Timestamp getCraetedAt() {
        return craetedAt;
    }

    public void setCraetedAt(Timestamp createdAt) {
        this.craetedAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "last_login_time", nullable = true)
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (sex != that.sex) return false;
        if (birthday != that.birthday) return false;
        if (payPoints != that.payPoints) return false;
        if (regTime != that.regTime) return false;
        if (lastLogin != that.lastLogin) return false;
        if (mobileValidated != that.mobileValidated) return false;
        if (emailValidated != that.emailValidated) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (paypwd != null ? !paypwd.equals(that.paypwd) : that.paypwd != null) return false;
        if (userMoney != null ? !userMoney.equals(that.userMoney) : that.userMoney != null) return false;
        if (frozenMoney != null ? !frozenMoney.equals(that.frozenMoney) : that.frozenMoney != null) return false;
        if (distributMoney != null ? !distributMoney.equals(that.distributMoney) : that.distributMoney != null)
            return false;
        if (lastIp != null ? !lastIp.equals(that.lastIp) : that.lastIp != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (oauth != null ? !oauth.equals(that.oauth) : that.oauth != null) return false;
        if (openid != null ? !openid.equals(that.openid) : that.openid != null) return false;
        if (unionid != null ? !unionid.equals(that.unionid) : that.unionid != null) return false;
        if (headPic != null ? !headPic.equals(that.headPic) : that.headPic != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (isLock != null ? !isLock.equals(that.isLock) : that.isLock != null) return false;
        if (isDistribut != null ? !isDistribut.equals(that.isDistribut) : that.isDistribut != null) return false;
        if (firstLeader != null ? !firstLeader.equals(that.firstLeader) : that.firstLeader != null) return false;
        if (secondLeader != null ? !secondLeader.equals(that.secondLeader) : that.secondLeader != null) return false;
        if (thirdLeader != null ? !thirdLeader.equals(that.thirdLeader) : that.thirdLeader != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (craetedAt != null ? !craetedAt.equals(that.craetedAt) : that.craetedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (paypwd != null ? paypwd.hashCode() : 0);
        result = 31 * result + (int) sex;
        result = 31 * result + birthday;
        result = 31 * result + (userMoney != null ? userMoney.hashCode() : 0);
        result = 31 * result + (frozenMoney != null ? frozenMoney.hashCode() : 0);
        result = 31 * result + (distributMoney != null ? distributMoney.hashCode() : 0);
        result = 31 * result + payPoints;
        result = 31 * result + regTime;
        result = 31 * result + lastLogin;
        result = 31 * result + (lastIp != null ? lastIp.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (int) mobileValidated;
        result = 31 * result + (oauth != null ? oauth.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (unionid != null ? unionid.hashCode() : 0);
        result = 31 * result + (headPic != null ? headPic.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (int) emailValidated;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (isLock != null ? isLock.hashCode() : 0);
        result = 31 * result + (isDistribut != null ? isDistribut.hashCode() : 0);
        result = 31 * result + (firstLeader != null ? firstLeader.hashCode() : 0);
        result = 31 * result + (secondLeader != null ? secondLeader.hashCode() : 0);
        result = 31 * result + (thirdLeader != null ? thirdLeader.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (craetedAt != null ? craetedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "userId=" + userId +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
