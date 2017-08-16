package com.jichuangtech.clothshopserver.model.vo;


/**
 * user_address表
 * @author zxx
 *
 */
public class UserAddressReqVO {
	private int addressId;
	private int userId;
	private String consignee;
	private long country = 86;
	private long province;
	private long city;
	private long district;
	private long twon = -1;
	private String address;
	private String zipcode = "000000";
	private String mobile;
	private byte isDefault = 1;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public long getCountry() {
		return country;
	}
	public void setCountry(long country) {
		this.country = country;
	}
	public long getProvince() {
		return province;
	}
	public void setProvince(long province) {
		this.province = province;
	}
	public long getCity() {
		return city;
	}
	public void setCity(long city) {
		this.city = city;
	}
	public long getDistrict() {
		return district;
	}
	public void setDistrict(long district) {
		this.district = district;
	}
	public long getTwon() {
		return twon;
	}
	public void setTwon(long twon) {
		this.twon = twon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public byte getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(byte isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
