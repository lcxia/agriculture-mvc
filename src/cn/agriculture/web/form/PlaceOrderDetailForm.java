package cn.agriculture.web.form;

import javax.validation.constraints.Digits;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class PlaceOrderDetailForm {
	private String placeOrderDetailId;
	private String placeOrderId;
	private String commodityId;
	private String brandId;
	private String brandName;
	private String commodityName;
	private String supplierId;
	private String supplierName;
	private String weight;
	private String specifications;
	private String unit;
	private String benchmarkPrice;
	private String guidePrice;
	private String retailPrice;
	private String note;
	private String stock;
	@NotEmpty(field="购买数量",  message="{errors.required}")
	@Digits(fraction=0,integer = 10,  message="购买数量{errors.format}")
	private String purchaseCount;
	private String sumAmount;
	private String updateTime;
	private String updateUser;
	private String priceIncrement;
	private String sumWeight;
}
