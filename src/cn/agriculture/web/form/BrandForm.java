package cn.agriculture.web.form;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class BrandForm {
	private String brandId;
	private String supplierId;
	@NotEmpty(field="品牌名称",  message="{errors.required}")
	private String brandName;
	private String note;
	private String updateTime;
	private String updateUser;
}
