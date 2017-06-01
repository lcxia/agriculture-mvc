package cn.agriculture.web.form;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class ExpressListForm {

	private String expressListId;
	private String expressId;
	private String expressName;
	private String isUsed;
	private String orderId;
	private String usedDate;
	private String note;
	private String storage;
	@NotEmpty(field="快递单号开始",message="{errors.required}")
	private String expressListIdStart;
	@NotEmpty(field="快递单号结束",message="{errors.required}")
	private String expressListIdEnd;
	private String updateTime;
	private String updateUser;
}
