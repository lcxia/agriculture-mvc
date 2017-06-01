package cn.agriculture.web.form;

import lombok.Data;

@Data
public class ChargeConfirmForm {
	private String chargeId;
	private String amount;
	private String distributorId;
	private String chargeDate;
	private String updateTime;
	private String updateUser;
	private String status;
}
