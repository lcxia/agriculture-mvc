package cn.agriculture.web.form;

import java.util.List;

import lombok.Data;

@Data
public class GoodsForm {
	private String commodityId;
	private String type;
	private String brandId;
	private String brandName;
	private String supplierId;
	private String supplierName;
	private String commodityName;
	private String weight;
	private String isGift;
	private String specifications;
	private String unit;
	private String benchmarkPrice;
	private String guidePrice;
	private String retailPrice;
	private String competitionLevel;
	private String note;
	private String updateTime;
	private String updateUser;
	private String pictureId;
	private String pictureId1;
	private String pictureId2;
	private String pictureId3;
	private String pictureId4;
	private String pictureId5;
	private String pictureId6;
	private byte[] picture;
	private String stock;
	private String commodityTypeId;
	private String commodityTypeName;
	private List<GoodsForm> list;
	private String count;
	private String css;
	private String ph;
}
