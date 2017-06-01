package cn.agriculture.web.form;

import javax.validation.constraints.Digits;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class CommodityForm {
	private String commodityId;
	private String type;
	private String brandId;
	private String brandName;
	private String supplierId;
	private String supplierName;
	@NotEmpty(field="名称",  message="{errors.required}")
	private String commodityName;
	@NotEmpty(field="单品重量",  message="{errors.required}")
	@Digits(fraction = 2, integer = 10,message="单品重量{errors.format}")
	private String weight;
	private String isGift;
	@NotEmpty(field="规格",  message="{errors.required}")
	private String specifications;
	private String unit;
	@Digits(fraction = 2, integer = 10,message="基准价格{errors.format}")
	@NotEmpty(field="基准价格",  message="{errors.required}")
	private String benchmarkPrice;
	@Digits(fraction = 2, integer = 10,message="售前指导价格{errors.format}")
	@NotEmpty(field="售前指导价格",  message="{errors.required}")
	private String guidePrice;
	@Digits(fraction = 2, integer = 10,message="终端零售价格{errors.format}")
	@NotEmpty(field="终端零售价格",  message="{errors.required}")
	private String retailPrice;
	private String competitionLevel;
	private String note;
	private String updateTime;
	private String updateUser;
	private String pictureId,pictureId1,pictureId2,pictureId3,pictureId4,pictureId5,pictureId6;
	private byte[] picture,picture1,picture2,picture3,picture4,picture5,picture6;
	private String commodityTypeId;
	private String commodityTypeName;
}
