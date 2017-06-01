package cn.agriculture.web.form;

import javax.validation.constraints.Pattern;

import lombok.Data;
import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class UserForm {
	@NotEmpty(field="用户ID",  message="{errors.required}")
	private String userId;
	@NotEmpty(field="用户姓名",  message="{errors.required}")
	private String userName;
	@NotEmpty(field="用户密码",  message="{errors.required}")
	private String password;
	@NotEmpty(field="确认密码",  message="{errors.required}")
	private String passwordConfirm;
	@NotEmpty(field="身份证号",  message="{errors.required}")
	private String idCard;
	@Pattern(regexp = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[18]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-09)",message="生日{errors.format}")
	private String birthday;
	private String gender;
	@NotEmpty(field="所属公司",  message="{errors.required}")
	private String companyName;
	@NotEmpty(field="住址",  message="{errors.required}")
	private String address;
	@NotEmpty(field="电子邮箱",  message="{errors.required}")
	private String email;
	@NotEmpty(field="联系电话",  message="{errors.required}")
	private String telephone;
	@NotEmpty(field="所在部门",  message="{errors.required}")
	private String department;
	@NotEmpty(field="职位",  message="{errors.required}")
	private String position;
	
	private String updateTime;

	private String updateUser;
	//@NotEmpty(field="",  message="{errors.required}")
	private String admin;
	private String priceIncrement;
	private String amount;
}
