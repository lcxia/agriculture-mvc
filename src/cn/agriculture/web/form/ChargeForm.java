package cn.agriculture.web.form;

import javax.validation.constraints.Digits;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class ChargeForm {
	private String chargeId;
	@NotEmpty(field="充值金额",  message="{errors.required}")
	@Digits(fraction = 2, integer = 10,message="充值金额{errors.format}")
	private String amount;
	private String distributorId;
	private String chargeDate;
	private String updateTime;
	private String updateUser;
	private String status;
}
