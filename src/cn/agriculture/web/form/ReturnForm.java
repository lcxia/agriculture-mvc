package cn.agriculture.web.form;

import lombok.Data;

@Data
public class ReturnForm {
	private String out_trade_no;
	private String trade_no;
	private String trade_status;
	private String notify_id;
	private String sign;
	private String sign_type;
	private String discount;
	private String payment_type;
	private String subject;
	private String buyer_email;
	private String gmt_create;
	private String notify_type;
	private String quantity;
	private String seller_id;
	private String notify_time;
	private String body;
	private String is_total_fee_adjust;
	private String total_fee;
	private String gmt_payment;
	private String seller_email;
	private String price;
	private String buyer_id;
	private String use_coupon;
}
