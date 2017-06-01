package cn.agriculture.web.form;

import lombok.Data;

@Data
public class AlipayReportForm {

	// 商户订单号
	private String outTradeNo;
	// 订单名称
	private String subject;
	// 付款金额
	private String price;
	// 订单描述
	private String body;
	// 收货人姓名
	private String receiveName;
	// 收货人地址
	private String receiveAddress;
	// 收货人邮编
	private String receiveZip;
	// 收货人电话号码
	private String receivePhone;
	// 收货人手机号码
	private String receiveMobile;
	private String isPaid;
	private String guestId;
	private String updateTime;
	private String updateUser;
	

}
