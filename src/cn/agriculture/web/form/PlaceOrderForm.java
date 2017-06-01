package cn.agriculture.web.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class PlaceOrderForm {

	private String placeOrderId;
	private String storage;
	private String expressId;
	private String transportMode;
	private String guestFrom;
	@NotEmpty(field="客户单位",  message="{errors.required}")
	private String guestCompany;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	@NotEmpty(field="地址",  message="{errors.required}")
	private String address;
	@NotEmpty(field="联系人",  message="{errors.required}")
	private String contacts;
	@NotEmpty(field="手机",  message="{errors.required}")
	private String mobile;
	@Digits(fraction = 0, integer = 6,message="邮政编码{errors.format}")
	@Length(min=6,max=6,message="{errors.length}")
	private String zip;
	@NotEmpty(field="固定电话",  message="{errors.required}")
	private String telephone;
	@Pattern(regexp = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[18]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-09)",message="日期{errors.format}")
	private String orderDate;
	private String note;
	private String amount;
	private String updateTime;
	private String updateUser;
	private String userId;
	private String status;
	private String weight;
}
