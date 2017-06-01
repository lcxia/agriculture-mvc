package cn.agriculture.web.form;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class ExpressForm {
	private String expressId;
	@NotEmpty(field="快递商名称",  message="{errors.required}")
	private String expressName;
	@NotEmpty(field="联系人",  message="{errors.required}")
	private String contacts;
	@NotEmpty(field="手机",  message="{errors.required}")
	private String mobile;
	@NotEmpty(field="传真",  message="{errors.required}")
	private String fax;
	@NotEmpty(field="固定电话",  message="{errors.required}")
	private String telephone;
	@NotEmpty(field="地址",  message="{errors.required}")
	private String address;
	private String status;
	private String note;
	private String updateTime;
	private String updateUser;
}
