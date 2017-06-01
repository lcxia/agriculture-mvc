package cn.agriculture.web.form;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class SupplierForm {
	private String SupplierId;
	private String type;
	private String level;
	@NotEmpty(field="供应商名称",  message="{errors.required}")
	private String supplierName;
	private String province;
	private String city;
	@NotEmpty(field="供应商地址",  message="{errors.required}")
	private String address;
	@NotEmpty(field="联系人",  message="{errors.required}")
	private String contacts;
	@NotEmpty(field="传真",  message="{errors.required}")
	private String fax;
	@NotEmpty(field="移动电话",  message="{errors.required}")
	private String telephone;
	@NotEmpty(field="固定电话",  message="{errors.required}")
	private String mobile;
	@NotEmpty(field="邮件",  message="{errors.required}")
	private String email;
	@NotEmpty(field="qq",  message="{errors.required}")
	private String qq;
	private String note;
	private String updateTime;
	private String updateUser;
}
