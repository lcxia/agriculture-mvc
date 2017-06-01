package cn.agriculture.web.form;

//import javax.validation.constraints.Digits;

import javax.validation.constraints.Pattern;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class DistributorForm {
	@Pattern(regexp ="[A-Za-z0-9]+$",message="分销商登录ID必须为数字或字母组成")
	@NotEmpty(field="分销商登录ID",  message="{errors.required}")
	private String distributorId;
	@NotEmpty(field="密码",  message="{errors.required}")
	private String password;
	@NotEmpty(field="新密码",  message="{errors.required}")
	private String passwordNew;
	@NotEmpty(field="密码确认",  message="{errors.required}")
	private String passwordConfirm;
	@NotEmpty(field="名称",  message="{errors.required}")
	private String distributorName;
	private String type;
	private String level;
	@NotEmpty(field="打印名称",  message="{errors.required}")
	private String printName;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	@NotEmpty(field="地址",  message="{errors.required}")
	private String address;
	@NotEmpty(field="联系人",  message="{errors.required}")
	private String contacts;
	@NotEmpty(field="传真",  message="{errors.required}")
	private String fax;
	@NotEmpty(field="固定电话",  message="{errors.required}")
	private String telephone;
	@NotEmpty(field="移动电话",  message="{errors.required}")
//	@Digits(fraction = 0, integer = 11,message="{errors.mobile}")
	private String mobile;
	@NotEmpty(field="Email",  message="{errors.required}")
	private String email;
	@NotEmpty(field="微信",  message="{errors.required}")
	private String wechat;
	@NotEmpty(field="QQ",  message="{errors.required}")
	private String qq;
	@NotEmpty(field="支付宝账号",  message="{errors.required}")
	private String alipay;
	private String note;
	private String updateTime;
	private String updateUser;
	private String priceIncrement;
	private String amount;
}
