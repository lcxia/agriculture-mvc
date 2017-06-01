package cn.agriculture.web.form;

import lombok.Data;

@Data
public class DeliveryForm {

	private String placeOrderId;
	private String storage;
	private String transportMode;
	private String guestFrom;
	private String guestCompany;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private String address;
	private String contacts;
	private String mobile;
	private String zip;
	private String telephone;
	private String orderDate;
	private String note;
	private String amount;
	private String updateTime;
	private String updateUser;
	private String userId;
	private String status;
	private String expressListId;
	private String isUsed;
	private String separateWeight;
	private String firstHeavyPrice;
	private String continuedHeavyPrice;
	private String expressPrice;
	private String expressId;
}
