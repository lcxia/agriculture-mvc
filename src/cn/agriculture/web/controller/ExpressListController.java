package cn.agriculture.web.controller;

import java.io.IOException;
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
import cn.agriculture.web.form.ExpressListForm;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.ExpressListService;

@Slf4j
@Controller("ExpressListController")
@RequestMapping("/")
public class ExpressListController {

	@Autowired
	ExpressListService expressListService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initExpressList", method = RequestMethod.GET)
    public String initExpressList(Model model) {
    	log.info("快递单号列表初始化");
    	model.addAttribute("list", expressListService.searchExpressListList());
        return "manager/expressList/expressListList";
    }
    
    @RequestMapping(value = "initAddExpressList", method = RequestMethod.GET)
    public String initAddStock(Model model) {
    	log.info("追加快递单号初始化");
    	List<Item> expressList = itemListComponent.getExpressList();
    	model.addAttribute("expressList", expressList);
    	ExpressListForm expressListForm = new ExpressListForm();
    	model.addAttribute("expressListForm", expressListForm);
    	return "manager/expressList/addExpressList";
    }
    
	@RequestMapping(value = "addExpressList", method = RequestMethod.POST)
	public String executeAddExpressList(Model model, HttpSession session, @Valid @ModelAttribute("expressListForm") ExpressListForm expressListForm, BindingResult results) throws SQLException, IOException {
		if (results.hasErrors()){
    		log.info("内容验证出错");
    		List<Item> expressList = itemListComponent.getExpressList();
        	model.addAttribute("expressList", expressList);    		
    		model.addAttribute("list", expressListService.searchExpressListList());
            return "manager/expressList/addExpressList";
    	}
		log.info("添加快递单号信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		expressListForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		expressListForm.setUpdateTime(dateformat.format(date));
		boolean result = expressListService.addExpressList(expressListForm);
		if(!result) {
			throw new SQLException("快递单号信息添加失败！");
		}
		model.addAttribute("list", expressListService.searchExpressListList());
        return "manager/expressList/expressListList";
	}
}
