package cn.agriculture.web.form;

import lombok.Data;

@Data
public class RebateForm {
	private String distributorId;
	private String distributorName;
	private String commodityId;
	private String commodityName;
	private String benchmarkPrice;
	private String retailPrice;
	private String rebateValue;
	private String priceIncrement;
	private String isRebate;
	private String amount;
	private String outTradeNo;
	private String updateTime;
	private String updateUser;
}
