package cn.agriculture.web.form;

import java.util.List;

import lombok.Data;

@Data
public class CartForm {

	private String cartId;
	private String commodityId;
	private String guestId;
	private String count;
	private String updateTime;
	private String updateUser;
	private String type;
	private String supplierName;
	private String brandName;
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
	private String pictureId;
	private String status;
	private List<ListBean> listBean;
	private String smallSumPrice;
	private String sumPrice;
	private String stock;
}

