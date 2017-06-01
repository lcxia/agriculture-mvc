package cn.agriculture.web.controller;

import java.io.IOException;
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

import cn.agriculture.web.form.ExpressForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.ExpressService;

@Slf4j
@Controller("ExpressController")
@RequestMapping("/")
public class ExpressController {

	@Autowired
	private ExpressService expressService;
	
    @RequestMapping(value = "initExpress", method = RequestMethod.GET)
    public String initExpress(Model model) {
    	log.info("快递商列表初始化");
    	model.addAttribute("list", expressService.searchExpressList());
        return "manager/express/expressList";
    }
    
    @RequestMapping(value = "initAddExpress", method = RequestMethod.GET)
    public String initAddExpress(Model model) {
    	log.info("追加快递商初始化");
    	ExpressForm expressForm = new ExpressForm();
    	model.addAttribute("expressForm", expressForm);
    	return "manager/express/addExpress";
    }
    
	@RequestMapping(value = "addExpress", method = RequestMethod.POST)
	public String executeAddExpress(Model model, HttpSession session, @Valid @ModelAttribute("expressForm") ExpressForm expressForm, BindingResult results) throws SQLException, IOException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			return "manager/express/addExpress";
		}
		log.info("添加快递商信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		expressForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		expressForm.setUpdateTime(dateformat.format(date));
		boolean result = expressService.addExpress(expressForm);
		if(!result) {
			throw new SQLException("快递商信息添加失败！");
		}
		model.addAttribute("list", expressService.searchExpressList());
        return "manager/express/expressList";
	}
	
	@RequestMapping(value = "initEditExpress", method = RequestMethod.GET)
	public String initEditExpress(Model model, ExpressForm expressForm) {
		log.info("修改快递商信息初始化");
		ExpressForm result = expressService.searchExpress(expressForm);
		model.addAttribute("expressForm", result);
		return "manager/express/editExpress";
	}
	
	@RequestMapping(value = "editExpress", method = RequestMethod.POST)
	public String executeEditExpress(Model model, HttpSession session, @Valid @ModelAttribute("expressForm") ExpressForm expressForm, BindingResult results) throws SQLException, IOException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			return "manager/express/addExpress";
		}
		log.info("修改快递商信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		expressForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		expressForm.setUpdateTime(dateformat.format(date));
		boolean result = expressService.editExpress(expressForm);
		if(!result) {
			throw new SQLException("快递商信息更新失败！");
		}
		model.addAttribute("list", expressService.searchExpressList());
        return "manager/express/expressList";
	}
	
	@RequestMapping(value = "delExpress", method = RequestMethod.GET)
	public String executeDelExpress(ExpressForm expressForm, Model model) throws Exception {
		log.info("删除快递商信息");
		boolean result = expressService.delExpress(expressForm);
		if(!result) {
			throw new SQLException("快递商信息删除失败！");
		}
		model.addAttribute("list", expressService.searchExpressList());
		return "manager/express/expressList";
	}
}
