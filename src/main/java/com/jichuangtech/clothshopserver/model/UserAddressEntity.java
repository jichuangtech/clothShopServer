package com.jichuangtech.clothshopserver.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user_address表
 * @author zxx
 *
 */
@Entity
@Table(name = "user_address", schema = "clothShop", catalog = "")
public class UserAddressEntity {
	private int addressId;
	private int userId;
	private String consignee;
	private long country;
	private long province;
	private long city;
	private long district;
	private long twon;
	private String address;
	private String zipcode;
	private String mobile;
	private byte isDefault;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)  
    @Column(name = "address_id", nullable = false)
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	@Basic
    @Column(name = "user_id", nullable = false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Basic
    @Column(name = "consignee", nullable = false)
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	@Basic
    @Column(name = "country", nullable = false)
	public long getCountry() {
		return country;
	}
	public void setCountry(long country) {
		this.country = country;
	}
	@Basic
    @Column(name = "province", nullable = false)
	public long getProvince() {
		return province;
	}
	public void setProvince(long province) {
		this.province = province;
	}
	@Basic
    @Column(name = "city", nullable = false)
	public long getCity() {
		return city;
	}
	public void setCity(long city) {
		this.city = city;
	}
	@Basic
    @Column(name = "district", nullable = false)
	public long getDistrict() {
		return district;
	}
	public void setDistrict(long district) {
		this.district = district;
	}
	@Basic
    @Column(name = "twon", nullable = false)
	public long getTwon() {
		return twon;
	}
	public void setTwon(long twon) {
		this.twon = twon;
	}
	@Basic
    @Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Basic
    @Column(name = "zipcode", nullable = false)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Basic
    @Column(name = "mobile", nullable = false)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Basic
    @Column(name = "is_default", nullable = false)
	public byte getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(byte isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
