package cn.agriculture.web.form;

import javax.validation.constraints.Digits;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class ExpressPriceForm {

	private String expressPriceId;
	private String expressId;
	private String provinceId;
	private String provinceName;
	@Digits(fraction = 2, integer = 10,message="分隔重量{errors.format}")
	@NotEmpty(field="分隔重量g",message="{errors.required}")
	private String separateWeight;
	@Digits(fraction = 2, integer = 10,message="首重费（元）{errors.format}")
	@NotEmpty(field="首重费（元）",message="{errors.required}")
	private String firstHeavyPrice;
	@Digits(fraction = 2, integer = 10,message="续重费（元）{errors.format}")
	@NotEmpty(field="续重费（元）",message="{errors.required}")
	private String continuedHeavyPrice;
	private String status;
	private String note;
	private String updateTime;
	private String updateUser;
	
}
