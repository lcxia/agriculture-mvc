package cn.agriculture.web.form;

import java.util.List;

import javax.validation.constraints.Digits;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class SpecialForm {
	// 商户订单号
	private String outTradeNo;
	// 订单名称
	private String subject;
	// 付款金额
	private String price;
	// 订单描述
	private String body;
	// 商品展示地址
	private String showUrl;
	// 收货人姓名
	@NotEmpty(field="收货人姓名",  message="{errors.required}")
	private String receiveName;
	// 收货人地址
	@NotEmpty(field="收货人地址",  message="{errors.required}")
	private String receiveAddress;
	// 收货人邮编
	@Digits( fraction = 0, integer = 6,message="邮政编码{errors.format}")
	@Length(min=6,max=6,message="{errors.length}")
	private String receiveZip;
	// 收货人电话号码
	@NotEmpty(field="收货人电话号码",  message="{errors.required}")
	private String receivePhone;
	// 收货人手机号码
	@NotEmpty(field="收货人手机号码",  message="{errors.required}")
	private String receiveMobile;
	private String updateTime;
	private String updateUser;
	private String commodityId;
	private String commodityName;
	private String retailPrice;
	private String specialIp;
	private String stock;
	private Integer index = 0;
	private List<CartForm> cartFormList;
}
