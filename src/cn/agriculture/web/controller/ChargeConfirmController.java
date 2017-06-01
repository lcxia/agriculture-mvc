package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.ChargeConfirmForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.ChargeConfirmService;

@Slf4j
@Controller("ChargeConfirmController")
@RequestMapping("/")
public class ChargeConfirmController {

	@Autowired
	ChargeConfirmService chargeConfirmService;
	
    @RequestMapping(value = "initChargeConfirm", method = RequestMethod.GET)
    public String initChargeConfirm(Model model) {
    	log.info("充值列表初始化");
    	model.addAttribute("list", chargeConfirmService.searchChargeConfirmList());
        return "manager/chargeConfirm/chargeConfirmList";
    }
    
	@RequestMapping(value = "editChargeConfirm", method = RequestMethod.GET)
	public String executeEditChargeConfirm(Model model, HttpSession session, ChargeConfirmForm chargeConfirmForm) throws SQLException {
		log.info("更新充值信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		chargeConfirmForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		chargeConfirmForm.setUpdateTime(dateformat.format(date));
		boolean result = chargeConfirmService.editChargeConfirm(chargeConfirmForm);
		if(!result) {
			throw new SQLException("充值信息更新失败！");
		}
		model.addAttribute("list", chargeConfirmService.searchChargeConfirmList());
		return "manager/chargeConfirm/chargeConfirmList";
	}
}
