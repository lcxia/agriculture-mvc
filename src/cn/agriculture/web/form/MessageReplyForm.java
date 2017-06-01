package cn.agriculture.web.form;

import lombok.Data;

@Data
public class MessageReplyForm {
	private String messageReplyId;
	private String messageId;
	private String context;
	private String replyerId;
	private String updateTime;
	private String updateUser;
}
