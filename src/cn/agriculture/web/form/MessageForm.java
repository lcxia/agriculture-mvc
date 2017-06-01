package cn.agriculture.web.form;

import lombok.Data;

@Data
public class MessageForm {
	private String messageId;
	private String distributorId;
	private String title;
	private String context;
	private String updateTime;
	private String updateUser;
}
