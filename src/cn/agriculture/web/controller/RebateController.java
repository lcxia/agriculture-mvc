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

import cn.agriculture.web.form.RebateForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.RebateService;

@Slf4j
@Controller("RebateController")
@RequestMapping("/")
public class RebateController {

	@Autowired
	RebateService rebateService;
	
	@RequestMapping(value = "initRebate", method = RequestMethod.GET)
	public String initRebate(Model model) {
		log.info("分销商返点列表初始化");
		model.addAttribute("list", rebateService.searchRebateList());
        return "manager/rebate/rebateList";
	}
	
	@RequestMapping(value = "editRebate", method = RequestMethod.GET)
	public String executeEditRebate(Model model, HttpSession session, RebateForm rebateForm) throws SQLException {
		log.info("将分销商返点充入分销商账户");
		UVO uvo = (UVO)session.getAttribute("UVO");
		rebateForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		rebateForm.setUpdateTime(dateformat.format(date));
		boolean result = rebateService.editRebate(rebateForm);
		if (!result) {
			throw new SQLException("返点充值失败！");
		}
		model.addAttribute("list", rebateService.searchRebateList());
        return "manager/rebate/rebateList";
	}
}
