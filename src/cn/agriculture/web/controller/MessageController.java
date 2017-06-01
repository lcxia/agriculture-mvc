package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import cn.agriculture.common.component.ItemListComponent;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.MessageForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.MessageService;

@Slf4j
@Controller("MessageController")
@RequestMapping("/")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initMessage", method = RequestMethod.GET)
    public String initMessage(Model model, HttpSession session) {
    	log.info("留言板列表初始化");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if ("on".equals(uvo.getAdmin())) {
    		model.addAttribute("list", messageService.searchMessageList());
    	} else {
    		MessageForm messageForm = new MessageForm();
        	messageForm.setDistributorId(uvo.getUserId());
        	model.addAttribute("list", messageService.searchMessageList(messageForm));
    	}
    	
    	return "manager/message/messageList";
    }
    
    @RequestMapping(value = "initAddMessage", method = RequestMethod.GET)
    public String initAddMessage(Model model, MessageForm messageForm, HttpSession session) {
    	log.info("添加留言初始化");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	model.addAttribute("messageForm", messageForm);
    	if ("on".equals(uvo.getAdmin())) {
    		List<Item> distributorList = itemListComponent.getDistributorList();
        	model.addAttribute("distributorList", distributorList);
    	}
    	return "manager/message/addMessage";
    }
    
    @RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public String executeAddMessage(Model model, HttpSession session, @Valid @ModelAttribute("messageForm") MessageForm messageForm, BindingResult results) throws SQLException {
    	log.info("添加留言");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (!"on".equals(uvo.getAdmin())) {
    		messageForm.setDistributorId(uvo.getUserId());
    	}
    	messageForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		messageForm.setUpdateTime(dateformat.format(date));
		boolean result = messageService.addMessage(messageForm);
		if (!result) {
			throw new SQLException("添加留言失败！");
		}
    	model.addAttribute("list", messageService.searchMessageList(messageForm));
    	return "manager/message/messageList";
    }
    
    @RequestMapping(value = "delMessage", method = RequestMethod.GET)
    public String delMessage(Model model, HttpSession session, MessageForm messageForm) throws SQLException {
    	log.info("删除留言");
    	boolean result = messageService.delMessage(messageForm);
		if (!result) {
			throw new SQLException("删除留言失败！");
		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if ("on".equals(uvo.getAdmin())) {
    		model.addAttribute("list", messageService.searchMessageList());
    	} else {
        	messageForm.setDistributorId(uvo.getUserId());
        	model.addAttribute("list", messageService.searchMessageList(messageForm));
    	}
    	
    	return "manager/message/messageList";
    }
}
