package cn.agriculture.web.form;

import javax.validation.constraints.Digits;

import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import cn.agriculture.common.validator.constraints.NotEmpty;

@Data
public class GuestForm {
	@NotEmpty(field="用户ID",  message="{errors.required}")
	private String guestId;
	@NotEmpty(field="用户姓名",  message="{errors.required}")
	private String guestName;
	@NotEmpty(field="用户密码",  message="{errors.required}")
	private String password;
	private String passwordConfirm;
	private String gender;
	@Email(message="email{errors.format}")
	private String email;
	private String qq;
	@NotEmpty(field="电话号码",  message="{errors.required}")
	private String mobile;
	private String phone;
	@Digits( fraction = 0, integer = 6,message="邮政编码{errors.format}")
	@Length(min=6,max=6,message="{errors.length}")
	private String zip;
	private String updateTime;
	private String updateUser;
	private boolean checkbox;
	private String addressId;
}
