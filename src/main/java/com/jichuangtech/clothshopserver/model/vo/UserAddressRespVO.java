package com.jichuangtech.clothshopserver.model.vo;


/**
 * user_address表
 * @author zxx
 *
 */
public class UserAddressRespVO {
	private int addressId;
	private int userId;
	private String consignee;
	private String address;
	private String zipcode;
	private String mobile;
	private byte isDefault;
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
