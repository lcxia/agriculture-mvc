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
import cn.agriculture.web.form.DistributorForm;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.PlaceOrderDetailForm;
import cn.agriculture.web.form.PlaceOrderForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.DistributorService;
import cn.agriculture.web.service.ExpressService;
import cn.agriculture.web.service.PlaceOrderDetailService;
import cn.agriculture.web.service.PlaceOrderService;

@Slf4j
@Controller("PlaceOrderController")
@RequestMapping("/")
public class PlaceOrderController {

	@Autowired
	PlaceOrderService placeOrderService;
	
	@Autowired
	PlaceOrderDetailService placeOrderDetailService;
	
	@Autowired
	DistributorService distributorService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
	@Autowired
	ExpressService expressService;
	
    @RequestMapping(value = "initPlaceOrder", method = RequestMethod.GET)
    public String initPlaceOrder(Model model, HttpSession session) {
    	log.info("销售订单列表初始化");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	PlaceOrderForm placeOrderForm = new PlaceOrderForm();
    	placeOrderForm.setUserId(uvo.getUserId());
    	model.addAttribute("list", placeOrderService.searchPlaceOrderList(placeOrderForm));
        return "manager/placeOrder/placeOrderList";
    }
    
    @RequestMapping(value = "initAddPlaceOrder", method = RequestMethod.GET)
    public String initAddPlaceOrder(Model model) {
    	log.info("追加销售订单初始化");
    	List<Item> provinceList = itemListComponent.getProvinceList();
    	model.addAttribute("provinceList", provinceList);
    	List<Item> expressList = itemListComponent.getExpressList();
    	model.addAttribute("expressList", expressList);
    	PlaceOrderForm placeOrderForm = new PlaceOrderForm();
    	model.addAttribute("placeOrderForm", placeOrderForm);
    	return "manager/placeOrder/addPlaceOrder";
    }
    
	@RequestMapping(value = "addPlaceOrder", method = RequestMethod.POST)
	public String executeAddPlaceOrder(Model model, HttpSession session, @Valid @ModelAttribute("placeOrderForm") PlaceOrderForm placeOrderForm, BindingResult results) throws SQLException {
		if(results.hasErrors())
		{
			log.info("添加销售订单信息");
			List<Item> provinceList = itemListComponent.getProvinceList();
	    	model.addAttribute("provinceList", provinceList);
	    	List<Item> expressList = itemListComponent.getExpressList();
	    	model.addAttribute("expressList", expressList);
	    	model.addAttribute("placeOrderForm", placeOrderForm);
			return "manager/placeOrder/addPlaceOrder";
		}
		UVO uvo = (UVO)session.getAttribute("UVO");
		placeOrderForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		placeOrderForm.setUpdateTime(dateformat.format(date));
		placeOrderForm.setUserId(uvo.getUserId());
		boolean result = placeOrderService.addPlaceOrder(placeOrderForm);
		if(!result) {
			throw new SQLException("销售订单信息添加失败！");
		}		
		model.addAttribute("list", placeOrderService.searchPlaceOrderList(placeOrderForm));
        return "manager/placeOrder/placeOrderList";
	}
	
	@RequestMapping(value = "initEditPlaceOrder", method = RequestMethod.GET)
	public String initEditPlaceOrder(Model model, PlaceOrderForm placeOrderForm) {
		log.info("修改销售订单信息初始化");
		PlaceOrderForm result = placeOrderService.searchPlaceOrder(placeOrderForm);
		model.addAttribute("placeOrderForm", result);
		List<Item> provinceList = itemListComponent.getProvinceList();
		List<Item> cityList = itemListComponent.getCityList(result.getProvinceId());
    	model.addAttribute("provinceList", provinceList);
    	model.addAttribute("cityList", cityList);
    	List<Item> expressList = itemListComponent.getExpressList();
    	model.addAttribute("expressList", expressList);
		return "manager/placeOrder/editPlaceOrder";
	}
	
	@RequestMapping(value = "editPlaceOrder", method = RequestMethod.POST)
	public String executeEditPlaceOrder(Model model, HttpSession session, @Valid @ModelAttribute("placeOrderForm") PlaceOrderForm placeOrderForm, BindingResult results) throws SQLException {
		if(results.hasErrors())
		{
			log.info("修改销售订单信息");
			List<Item> provinceList = itemListComponent.getProvinceList();
			model.addAttribute("provinceList", provinceList);
			List<Item> cityList = itemListComponent.getCityList(placeOrderForm.getProvinceId());
			model.addAttribute("cityList", cityList);
	    	List<Item> expressList = itemListComponent.getExpressList();
	    	model.addAttribute("expressList", expressList);
	    	model.addAttribute("placeOrderForm", placeOrderForm);
	    	return "manager/placeOrder/editPlaceOrder";
		}
		
		UVO uvo = (UVO)session.getAttribute("UVO");
		placeOrderForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		placeOrderForm.setUpdateTime(dateformat.format(date));
		placeOrderForm.setUserId(uvo.getUserId());
		
		PlaceOrderDetailForm placeOrderDetailForm = new PlaceOrderDetailForm();
		placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
		placeOrderDetailForm.setPlaceOrderId(placeOrderForm.getPlaceOrderId());
		List<PlaceOrderDetailForm> list = placeOrderDetailService.searchPlaceOrderDetailList(placeOrderDetailForm);
		Double amount = 0d;
		Double weight = 0d;
		for (PlaceOrderDetailForm placeOrderDetail : list) {
			amount = amount + Double.valueOf(placeOrderDetail.getSumAmount());
			weight = weight + Double.valueOf(placeOrderDetail.getSumWeight());
		}
		// 计算运费
		placeOrderForm.setAmount(String.valueOf(amount));
		placeOrderForm.setWeight(String.valueOf(weight));
		
		boolean result = placeOrderService.editPlaceOrder(placeOrderForm);		
		if(!result) {
			throw new SQLException("销售订单信息更新失败！");
		}
		model.addAttribute("list", placeOrderService.searchPlaceOrderList(placeOrderForm));
		return "manager/placeOrder/placeOrderList";
	}
	
	@RequestMapping(value = "delPlaceOrder", method = RequestMethod.GET)
	public String executeDelExpressPrice(Model model, HttpSession session, PlaceOrderForm placeOrderForm) throws SQLException {
		log.info("删除销售订单信息");
		boolean result = placeOrderService.delPlaceOrder(placeOrderForm);
		if(!result) {
			throw new SQLException("销售订单信息删除失败！");
		}
		UVO uvo = (UVO)session.getAttribute("UVO");
    	PlaceOrderForm param = new PlaceOrderForm();
    	param.setUserId(uvo.getUserId());
		model.addAttribute("list", placeOrderService.searchPlaceOrderList(param));
		return "manager/placeOrder/placeOrderList";
	}
	
	@RequestMapping(value = "submitPlaceOrder", method = RequestMethod.GET)
	public String executeSubmitPlaceOrder(Model model, HttpSession session, PlaceOrderForm placeOrderForm) throws SQLException {
		log.info("提交销售订单");
		UVO uvo = (UVO)session.getAttribute("UVO");
		placeOrderForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		placeOrderForm.setUpdateTime(dateformat.format(date));
		
		PlaceOrderForm placeOrderForAmount = placeOrderService.searchPlaceOrder(placeOrderForm);
		DistributorForm distributorForm = new DistributorForm();
		distributorForm.setDistributorId(uvo.getUserId());
		DistributorForm distributorForAmount = distributorService.searchDistributor(distributorForm);
		Double amount = Double.valueOf(distributorForAmount.getAmount()) - Double.valueOf(placeOrderForAmount.getAmount());
		if (amount < 0) {
			model.addAttribute("message", "余额不足！余额:" + distributorForAmount.getAmount() + "需要:" + placeOrderForAmount.getAmount());
			placeOrderForm.setUserId(uvo.getUserId());
	    	model.addAttribute("list", placeOrderService.searchPlaceOrderList(placeOrderForm));
			return "manager/placeOrder/placeOrderList";
		}
		distributorForAmount.setAmount(String.valueOf(amount));
		if(!distributorService.editDistributorAmount(distributorForAmount)) {
			throw new SQLException("账户余额更新失败！");
		}
		
		boolean result = placeOrderService.submitPlaceOrder(placeOrderForm);
		if(!result) {
			throw new SQLException("库存不足，销售订单提交失败，请尽快联系管理员补货！");
		}
    	placeOrderForm.setUserId(uvo.getUserId());
    	model.addAttribute("list", placeOrderService.searchPlaceOrderList(placeOrderForm));
        return "manager/placeOrder/placeOrderList";
	}
}
