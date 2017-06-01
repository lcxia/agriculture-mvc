package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.MessageForm;
import cn.agriculture.web.form.MessageReplyForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.MessageReplyService;
import cn.agriculture.web.service.MessageService;

@Slf4j
@Controller("MessageReplyController")
@RequestMapping("/")
public class MessageReplyController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	MessageReplyService messageReplyService;
	
    @RequestMapping(value = "initMessageReply", method = RequestMethod.GET)
    public String initMessageReply(Model model, MessageReplyForm messageReplyForm) {
    	log.info("回复列表初始化");
    	MessageForm messageForm = new MessageForm();
    	messageForm.setMessageId(messageReplyForm.getMessageId());
    	
    	model.addAttribute("messageForm", messageService.searchMessage(messageForm));
    	model.addAttribute("list", messageReplyService.searchMessageReplyList(messageReplyForm));
    	return "manager/messageReply/messageReplyList";
    }
    
    @RequestMapping(value = "addMessageReply", method = RequestMethod.POST)
	public String executeAddMessageReply(Model model, HttpSession session, @Valid @ModelAttribute("messageReplyForm") MessageReplyForm messageReplyForm, BindingResult results) throws SQLException {
    	log.info("添加回复");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	messageReplyForm.setReplyerId(uvo.getUserId());
    	messageReplyForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		messageReplyForm.setUpdateTime(dateformat.format(date));
		boolean result = messageReplyService.addMessageReply(messageReplyForm);
		if (!result) {
			throw new SQLException("添加回复失败！");
		}
		MessageForm messageForm = new MessageForm();
    	messageForm.setMessageId(messageReplyForm.getMessageId());
		model.addAttribute("messageForm", messageService.searchMessage(messageForm));
    	model.addAttribute("list", messageReplyService.searchMessageReplyList(messageReplyForm));
    	return "manager/messageReply/messageReplyList";
    }
}
