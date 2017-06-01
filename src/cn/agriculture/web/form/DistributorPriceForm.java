package cn.agriculture.web.form;

import javax.validation.constraints.Digits;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class DistributorPriceForm {

	private String type;
	private String supplierName;
	private String brandName;
	private String specifications;
	private String commodityId;
	private String commodityName;
	private String guidePrice;
	private String benchmarkPrice;
	@NotEmpty(field="终端价",  message="{errors.required}")
	@Digits(fraction = 2, integer = 6,message="终端价{errors.format}")
	private String retailPrice;
	private String isGift;
	private String priceIncrement;
	private String distributorId;
	private String distributorName;
	private String distributorPriceId;
	private String updateTime;
	private String updateUser;
}
