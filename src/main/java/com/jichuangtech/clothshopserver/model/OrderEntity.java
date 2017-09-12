package com.jichuangtech.clothshopserver.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * order表
 * @author zxx
 *
 */
@Entity
@Table(name = "`order`", schema = "clothShop", catalog = "")
public class OrderEntity {
	private int orderId;
	private String orderSn;
	private int userId;
	private byte orderStatus;
	private byte shippingStatus;
	private byte payStatus;
	private String address;
	private String mobile;
	private String consignee;
	//订单总价
	private BigDecimal totalAmount;
	private int addressId;

	@Basic
	@Column(name = "address_id", nullable = false)
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)  
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	@Basic
    @Column(name = "order_sn", nullable = false, length = 20)
	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
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
    @Column(name = "order_status", nullable = false)
	public byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(byte orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Basic
    @Column(name = "shipping_status", nullable = false)
	public byte getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(byte shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	@Basic
    @Column(name = "pay_status", nullable = false)
	public byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(byte payStatus) {
		this.payStatus = payStatus;
	}


	@Basic
    @Column(name = "total_amount", nullable = false)
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
    @Column(name = "mobile", nullable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Basic
    @Column(name = "consignee", nullable = false)
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	
}
