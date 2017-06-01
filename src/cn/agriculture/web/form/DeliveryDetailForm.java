package cn.agriculture.web.form;

import lombok.Data;

@Data
public class DeliveryDetailForm {
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
	private String purchaseCount;
	private String sumAmount;
	private String updateTime;
	private String updateUser;
	private String priceIncrement;
	private String sumWeight;
}
