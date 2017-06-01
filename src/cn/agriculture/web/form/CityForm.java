package cn.agriculture.web.form;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class CityForm {
	private String provinceId;
	private String cityId;
	@NotEmpty(field="城市名称",  message="{errors.required}")
	private String cityName;
}
