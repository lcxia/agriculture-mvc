package cn.agriculture.web.form;


import javax.validation.constraints.Digits;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class ReceiveForm {
	private String addressId;
	private String guestId;
	@NotEmpty(field="地址",message="{errors.required}")
	private String addressName;
	private String  phone;
	@Digits( fraction = 0, integer = 11,message="手机号码{errors.format}")
	private String mobile;
	@NotEmpty(field="收货人姓名",message="{errors.required}")
    private String receiveName;
	@Digits( fraction = 0, integer = 6,message="邮政编码{errors.format}")
	@Length(min=6,max=6,message="{errors.length}")
    private String post;
    private String check;
}
