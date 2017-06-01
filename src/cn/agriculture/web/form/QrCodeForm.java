package cn.agriculture.web.form;

import lombok.Data;

@Data
public class QrCodeForm {
	private String distributorPriceId;
	private String distributorId;
	private String commodityId;
	private String commodityName;
	private String retailPrice;
}
