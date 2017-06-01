package cn.agriculture.web.form;
import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class CommodityTypeForm {
	private String commodityTypeId;
	@NotEmpty(field="商品类型",  message="{errors.required}")
	private String commodityTypeName;
	private String note;
	private String updateTime;
}
