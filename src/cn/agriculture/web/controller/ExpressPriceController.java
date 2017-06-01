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
import cn.agriculture.web.form.ExpressForm;
import cn.agriculture.web.form.ExpressPriceForm;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.ExpressPriceService;
import cn.agriculture.web.service.ExpressService;

@Slf4j
@Controller("ExpressPriceController")
@RequestMapping("/")
public class ExpressPriceController {

	@Autowired
	private ExpressPriceService expressPriceService;
	
	@Autowired
	private ExpressService expressService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initExpressPrice", method = RequestMethod.GET)
    public String initExpressPrice(Model model, ExpressPriceForm expressPriceForm) {
    	log.info("快递商所属价格列表初始化");
    	ExpressForm expressForm = new ExpressForm();
    	expressForm.setExpressId(expressPriceForm.getExpressId());
    	model.addAttribute("expressForm", expressService.searchExpress(expressForm));
    	model.addAttribute("list", expressPriceService.searchExpressPriceList(expressPriceForm));
        return "manager/expressPrice/expressPriceList";
    }
    
    @RequestMapping(value = "initAddExpressPrice", method = RequestMethod.GET)
    public String initAddExpressPrice(Model model, ExpressPriceForm expressPriceForm) {
    	log.info("追加快递商所属价格初始化");
    	List<Item> provinceList = itemListComponent.getProvinceList1(expressPriceForm.getExpressId());
    	model.addAttribute("provinceList", provinceList);
    	model.addAttribute("expressPriceForm", expressPriceForm);
        return "manager/expressPrice/addExpressPrice";
    }
    
    @RequestMapping(value = "addExpressPrice", method = RequestMethod.POST)
    public String executeAddExpressPrice(Model model, HttpSession session, @Valid @ModelAttribute("expressPriceForm") ExpressPriceForm expressPriceForm, BindingResult results) throws SQLException {
    	if (results.hasErrors()){
    		log.info("内容验证出错");
    		List<Item> provinceList = itemListComponent.getProvinceList();
        	model.addAttribute("provinceList", provinceList);
    		 return "manager/expressPrice/addExpressPrice";
    	}
    	log.info("追加快递商所属价格");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	expressPriceForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		expressPriceForm.setUpdateTime(dateformat.format(date));

		boolean result = expressPriceService.addExpressPrice(expressPriceForm);
		if(!result) {
			throw new SQLException("快递商所属价格追加失败！");
		}
		ExpressForm expressForm = new ExpressForm();
    	expressForm.setExpressId(expressPriceForm.getExpressId());
    	model.addAttribute("expressForm", expressService.searchExpress(expressForm));
    	model.addAttribute("list", expressPriceService.searchExpressPriceList(expressPriceForm));
        return "manager/expressPrice/expressPriceList";
    }
    
	@RequestMapping(value = "initEditExpressPrice", method = RequestMethod.GET)
	public String initEditExpressPrice(Model model, ExpressPriceForm expressPriceForm) {
		log.info("修改快递商所属价格初始化");
		model.addAttribute("expressPriceForm", expressPriceService.searchExpressPrice(expressPriceForm));
		return "manager/expressPrice/editExpressPrice";
	}
	
	@RequestMapping(value = "editExpressPrice", method = RequestMethod.POST)
	public String executeEditExpressPrice(Model model, HttpSession session, @Valid @ModelAttribute("expressPriceForm") ExpressPriceForm expressPriceForm, BindingResult results) throws SQLException {
		log.info("修改快递商所属价格");
		if (results.hasErrors()) {
			expressPriceForm.setProvinceId(expressPriceForm.getProvinceId());
			model.addAttribute("expressPriceForm", expressPriceForm);
			return "manager/expressPrice/editExpressPrice";
		}
		UVO uvo = (UVO)session.getAttribute("UVO");
		expressPriceForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		expressPriceForm.setUpdateTime(dateformat.format(date));
		boolean result = expressPriceService.editExpressPrice(expressPriceForm);
		if(!result) {
			throw new SQLException("快递商所属价格更新失败！");
		}
		ExpressForm expressForm = new ExpressForm();
    	expressForm.setExpressId(expressPriceForm.getExpressId());
    	model.addAttribute("expressForm", expressService.searchExpress(expressForm));
    	model.addAttribute("list", expressPriceService.searchExpressPriceList(expressPriceForm));
        return "manager/expressPrice/expressPriceList";
	}
	
	@RequestMapping(value = "delExpressPrice", method = RequestMethod.GET)
	public String executeDelExpressPrice(ExpressPriceForm expressPriceForm, Model model) throws SQLException {
		log.info("删除快递商所属价格");
		boolean result = expressPriceService.delExpressPrice(expressPriceForm);
		if(!result) {
			throw new SQLException("快递商所属价格删除失败！");
		}
		ExpressForm expressForm = new ExpressForm();
    	expressForm.setExpressId(expressPriceForm.getExpressId());
    	model.addAttribute("expressForm", expressService.searchExpress(expressForm));
    	model.addAttribute("list", expressPriceService.searchExpressPriceList(expressPriceForm));
        return "manager/expressPrice/expressPriceList";
	}
}
