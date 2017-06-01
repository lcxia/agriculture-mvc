package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.agriculture.common.component.ItemListComponent;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.PlaceOrderDetailForm;
import cn.agriculture.web.form.PlaceOrderForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CommodityService;
import cn.agriculture.web.service.PlaceOrderDetailService;
import cn.agriculture.web.service.PlaceOrderService;

@Slf4j
@Controller("PlaceOrderDetailController")
@RequestMapping("/")
public class PlaceOrderDetailController {

	@Autowired
	PlaceOrderService placeOrderService;
	
	@Autowired
	PlaceOrderDetailService placeOrderDetailService;
	
	@Autowired
	CommodityService commodityService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initPlaceOrderDetail", method = RequestMethod.GET)
    public String initPlaceOrderDetail(Model model, HttpSession session, PlaceOrderDetailForm placeOrderDetailForm) {
    	log.info("销售订单明细列表初始化");
    	PlaceOrderForm placeOrderForm = new PlaceOrderForm();
    	placeOrderForm.setPlaceOrderId(placeOrderDetailForm.getPlaceOrderId());
    	PlaceOrderForm result = placeOrderService.searchPlaceOrderForDetail(placeOrderForm);
		model.addAttribute("placeOrderForm", result);
		UVO uvo = (UVO)session.getAttribute("UVO");
		placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
		List<PlaceOrderDetailForm> list = placeOrderDetailService.searchPlaceOrderDetailList(placeOrderDetailForm);
    	model.addAttribute("list", list);
        return "manager/placeOrderDetail/placeOrderDetailList";
    }
    
    @RequestMapping(value = "initAddPlaceOrderDetail", method = RequestMethod.GET)
    public String initAddPlaceOrderDetail(Model model, PlaceOrderDetailForm placeOrderDetailForm) {
    	log.info("追加销售订单明细初始化");
    	List<Item> supplierList = itemListComponent.getSupplierList();
    	model.addAttribute("supplierList", supplierList);
    	model.addAttribute("placeOrderDetailForm", placeOrderDetailForm);
    	return "manager/placeOrderDetail/addPlaceOrderDetail";
    }
    
    @RequestMapping(value = "addPlaceOrderDetail", method = RequestMethod.POST)
    public String executeAddPlaceOrderDetail(Model model, HttpSession session, @Valid @ModelAttribute("placeOrderDetailForm") PlaceOrderDetailForm placeOrderDetailForm, BindingResult results) throws SQLException {
    	if (results.hasErrors()) {
			log.info("内容验证出错");
			List<Item> supplierList = itemListComponent.getSupplierList();
	    	model.addAttribute("supplierList", supplierList);
	    	model.addAttribute("placeOrderDetailForm", placeOrderDetailForm);
			return "manager/placeOrderDetail/addPlaceOrderDetail";
		}
    	log.info("追加销售订单明细");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	
    	PlaceOrderDetailForm checkResult = placeOrderDetailService.searchCommodityAndStock(placeOrderDetailForm.getCommodityId(), uvo.getPriceIncrement());
    	if (checkResult.getStock() == null) {
    		log.info("没有库存");
			List<Item> supplierList = itemListComponent.getSupplierList();
	    	model.addAttribute("supplierList", supplierList);
	    	model.addAttribute("placeOrderDetailForm", placeOrderDetailForm);
	    	model.addAttribute("message", "没有库存！");
			return "manager/placeOrderDetail/addPlaceOrderDetail";
    	} else {
    		if (Double.valueOf(checkResult.getStock()) < Double.valueOf(placeOrderDetailForm.getPurchaseCount())) {
    			log.info("库存不足");
    			List<Item> supplierList = itemListComponent.getSupplierList();
    	    	model.addAttribute("supplierList", supplierList);
    	    	model.addAttribute("placeOrderDetailForm", placeOrderDetailForm);
    	    	model.addAttribute("message", "库存不足！");
    			return "manager/placeOrderDetail/addPlaceOrderDetail";
    		}
    	}
    	placeOrderDetailForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		placeOrderDetailForm.setUpdateTime(dateformat.format(date));

		boolean result = placeOrderDetailService.addPlaceOrderDetail(placeOrderDetailForm);
		if(!result) {
			throw new SQLException("销售订单明细追加失败！");
		}
		PlaceOrderForm placeOrderForm = new PlaceOrderForm();
		placeOrderForm.setPlaceOrderId(placeOrderDetailForm.getPlaceOrderId());
    	PlaceOrderForm placeOrderFormResult = placeOrderService.searchPlaceOrderForDetail(placeOrderForm);
		model.addAttribute("placeOrderForm", placeOrderFormResult);
		placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
		List<PlaceOrderDetailForm> list = placeOrderDetailService.searchPlaceOrderDetailList(placeOrderDetailForm);
		Double amount = 0d;
		Double weight = 0d;
		for (PlaceOrderDetailForm placeOrderDetail : list) {
			amount = amount + Double.valueOf(placeOrderDetail.getSumAmount());
			weight = weight + Double.valueOf(placeOrderDetail.getSumWeight());
		}
		// 计算运费
		placeOrderFormResult.setAmount(String.valueOf(amount));
		placeOrderFormResult.setWeight(String.valueOf(weight));
		placeOrderService.editPlaceOrder(placeOrderFormResult);
    	model.addAttribute("list", list);
        return "manager/placeOrderDetail/placeOrderDetailList";
    }
    
	@RequestMapping(value = "initEditPlaceOrderDetail", method = RequestMethod.GET)
	public String initEditPlaceOrderDetail(Model model, HttpSession session, PlaceOrderDetailForm placeOrderDetailForm) {
		log.info("修改销售订单明细初始化");
		UVO uvo = (UVO)session.getAttribute("UVO");
		placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
    	PlaceOrderDetailForm result = placeOrderDetailService.searchPlaceOrderDetail(placeOrderDetailForm);
		model.addAttribute("placeOrderDetailForm", result);
		List<Item> supplierList = itemListComponent.getSupplierList();
    	model.addAttribute("supplierList", supplierList);
    	List<Item> brandList = itemListComponent.getBrandList(result.getSupplierId());
    	model.addAttribute("brandList", brandList);
    	List<Item> commodityList = itemListComponent.getCommodityList(result.getBrandId());
    	model.addAttribute("commodityList", commodityList);
		return "manager/placeOrderDetail/editPlaceOrderDetail";
	}
    
	@RequestMapping(value = "editPlaceOrderDetail", method = RequestMethod.POST)
	public String executeEditPlaceOrderDetail(Model model, HttpSession session, @Valid @ModelAttribute("placeOrderDetailForm") PlaceOrderDetailForm placeOrderDetailForm, BindingResult results) throws SQLException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			UVO uvo = (UVO)session.getAttribute("UVO");
			placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
	    	PlaceOrderDetailForm result = placeOrderDetailService.searchPlaceOrderDetail(placeOrderDetailForm);
			List<Item> supplierList = itemListComponent.getSupplierList();
	    	model.addAttribute("supplierList", supplierList);
	    	List<Item> brandList = itemListComponent.getBrandList(result.getSupplierId());
	    	model.addAttribute("brandList", brandList);
	    	List<Item> commodityList = itemListComponent.getCommodityList(result.getBrandId());
	    	model.addAttribute("commodityList", commodityList);
			return "manager/placeOrderDetail/editPlaceOrderDetail";
		}
		log.info("修改销售订单明细");
		UVO uvo = (UVO)session.getAttribute("UVO");
		
		PlaceOrderDetailForm checkResult = placeOrderDetailService.searchCommodityAndStock(placeOrderDetailForm.getCommodityId(), uvo.getPriceIncrement());
    	if (checkResult.getStock() == null) {
    		log.info("没有库存");
			placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
	    	PlaceOrderDetailForm result = placeOrderDetailService.searchPlaceOrderDetail(placeOrderDetailForm);
			model.addAttribute("placeOrderDetailForm", result);
			List<Item> supplierList = itemListComponent.getSupplierList();
	    	model.addAttribute("supplierList", supplierList);
	    	List<Item> brandList = itemListComponent.getBrandList(result.getSupplierId());
	    	model.addAttribute("brandList", brandList);
	    	List<Item> commodityList = itemListComponent.getCommodityList(result.getBrandId());
	    	model.addAttribute("commodityList", commodityList);
	    	model.addAttribute("message", "没有库存！");
			return "manager/placeOrderDetail/editPlaceOrderDetail";
    	} else {
    		if (Double.valueOf(checkResult.getStock()) < Double.valueOf(placeOrderDetailForm.getPurchaseCount())) {
    			log.info("库存不足");
    			placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
    	    	PlaceOrderDetailForm result = placeOrderDetailService.searchPlaceOrderDetail(placeOrderDetailForm);
    			model.addAttribute("placeOrderDetailForm", result);
    			List<Item> supplierList = itemListComponent.getSupplierList();
    	    	model.addAttribute("supplierList", supplierList);
    	    	List<Item> brandList = itemListComponent.getBrandList(result.getSupplierId());
    	    	model.addAttribute("brandList", brandList);
    	    	List<Item> commodityList = itemListComponent.getCommodityList(result.getBrandId());
    	    	model.addAttribute("commodityList", commodityList);
    	    	model.addAttribute("message", "库存不足！");
    			return "manager/placeOrderDetail/editPlaceOrderDetail";
    		}
    	}
		placeOrderDetailForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		placeOrderDetailForm.setUpdateTime(dateformat.format(date));
		boolean result = placeOrderDetailService.editPlaceOrderDetail(placeOrderDetailForm);
		if(!result) {
			throw new SQLException("销售订单明细更新失败！");
		}
		PlaceOrderForm placeOrderForm = new PlaceOrderForm();
		placeOrderForm.setPlaceOrderId(placeOrderDetailForm.getPlaceOrderId());
    	PlaceOrderForm placeOrderFormResult = placeOrderService.searchPlaceOrderForDetail(placeOrderForm);
		model.addAttribute("placeOrderForm", placeOrderFormResult);
		placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
		List<PlaceOrderDetailForm> list = placeOrderDetailService.searchPlaceOrderDetailList(placeOrderDetailForm);
		Double amount = 0d;
		Double weight = 0d;
		for (PlaceOrderDetailForm placeOrderDetail : list) {
			amount = amount + Double.valueOf(placeOrderDetail.getSumAmount());
			weight = weight + Double.valueOf(placeOrderDetail.getSumWeight());
		}
		// 计算运费
		placeOrderFormResult.setAmount(String.valueOf(amount));
		placeOrderFormResult.setWeight(String.valueOf(weight));
		placeOrderService.editPlaceOrder(placeOrderFormResult);
    	model.addAttribute("list", list);
        return "manager/placeOrderDetail/placeOrderDetailList";
	}
	
	@RequestMapping(value = "delPlaceOrderDetail", method = RequestMethod.GET)
	public String executeDelExpressPrice(Model model, HttpSession session, PlaceOrderDetailForm placeOrderDetailForm) throws SQLException {
		log.info("删除销售订单明细");
		boolean result = placeOrderDetailService.delPlaceOrderDetail(placeOrderDetailForm);
		if(!result) {
			throw new SQLException("销售订单明细删除失败！");
		}
		PlaceOrderForm placeOrderForm = new PlaceOrderForm();
		placeOrderForm.setPlaceOrderId(placeOrderDetailForm.getPlaceOrderId());
    	PlaceOrderForm placeOrderFormResult = placeOrderService.searchPlaceOrderForDetail(placeOrderForm);
		model.addAttribute("placeOrderForm", placeOrderFormResult);
		UVO uvo = (UVO)session.getAttribute("UVO");
		placeOrderDetailForm.setPriceIncrement(uvo.getPriceIncrement());
		List<PlaceOrderDetailForm> list = placeOrderDetailService.searchPlaceOrderDetailList(placeOrderDetailForm);
		Double amount = 0d;
		Double weight = 0d;
		for (PlaceOrderDetailForm placeOrderDetail : list) {
			amount = amount + Double.valueOf(placeOrderDetail.getSumAmount());
			weight = weight + Double.valueOf(placeOrderDetail.getSumWeight());
		}
		// 计算运费
		placeOrderFormResult.setAmount(String.valueOf(amount));
		placeOrderFormResult.setWeight(String.valueOf(weight));
		placeOrderService.editPlaceOrder(placeOrderFormResult);
    	model.addAttribute("list", list);
        return "manager/placeOrderDetail/placeOrderDetailList";
	}
	
    @RequestMapping(value = "getCommodity", method = RequestMethod.POST)
    public @ResponseBody Map<String, List<Map<String, String>>> getCommodity(String brandId) {
    	List<Item> commodityList = itemListComponent.getCommodityList(brandId);
    	List<Map<String, String>> list= new ArrayList<>();
    	for(Item item : commodityList) {
    		Map<String, String> commodityMap = new HashMap<>();
    		commodityMap.put("value", item.getValue());
    		commodityMap.put("label", item.getLabel());
    		list.add(commodityMap);
    	}
    	Map<String, List<Map<String, String>>> josnMap = new HashMap<>();
    	josnMap.put("commoditys", list);
    	return josnMap;
    }
    
    @RequestMapping(value = "getCommodityInfo", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> getCommodityInfo(String commodityId, HttpSession session) {
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	PlaceOrderDetailForm result = placeOrderDetailService.searchCommodityAndStock(commodityId, uvo.getPriceIncrement());
    	Map<String, String> josnMap = new HashMap<>();
    	josnMap.put("specifications", result.getSpecifications());
    	josnMap.put("unit", result.getUnit());
    	josnMap.put("weight", result.getWeight());
    	josnMap.put("benchmarkPrice", result.getBenchmarkPrice());
    	josnMap.put("guidePrice", result.getGuidePrice());
    	josnMap.put("retailPrice", result.getRetailPrice());
    	josnMap.put("stock", result.getStock());
    	return josnMap;
    }
}
