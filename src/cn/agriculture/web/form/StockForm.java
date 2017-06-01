package cn.agriculture.web.form;


import javax.validation.constraints.Digits;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class StockForm {
	
	private String stockId;
	
	@NotEmpty(field="商品",  message="{errors.required}")
	private String commodityId;
	
	private String commodityName;
	private String type;
	private String brandId;
	private String brandName;
	private String supplierId;
	private String supplierName;
	private String specifications;
	
	@Digits(fraction=0,integer = 10,  message="库存{errors.format}")
	private String stock;
	
	private String note;
	private String updateTime;
	private String updateUser;
}
