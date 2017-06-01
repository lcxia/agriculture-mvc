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
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.StockForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.StockService;

@Slf4j
@Controller("StockController")
@RequestMapping("/")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initStock", method = RequestMethod.GET)
    public String initStock(Model model) {
    	log.info("库存列表初始化");
    	model.addAttribute("list", stockService.searchStockList());
        return "manager/stock/stockList";
    }
    
    @RequestMapping(value = "initAddStock", method = RequestMethod.GET)
    public String initAddStock(Model model) {
    	log.info("追加库存单据初始化");
    	List<Item> commodityList = itemListComponent.getSurCommodityList();
    	model.addAttribute("commodityList", commodityList);
    	StockForm stockForm = new StockForm();
    	model.addAttribute("stockForm", stockForm);
    	return "manager/stock/addStock";
    }
    
	@RequestMapping(value = "addStock", method = RequestMethod.POST)
	public String executeAddStock(Model model, HttpSession session, @Valid @ModelAttribute("stockForm") StockForm stockForm, BindingResult results) throws SQLException, IOException {
		if (results.hasErrors()) {
			log.info("添加库存单据信息");
			List<Item> commodityList = itemListComponent.getSurCommodityList();
	    	model.addAttribute("commodityList", commodityList);
			model.addAttribute("stockForm", stockForm);
	        return "manager/stock/addStock";
		}
		
		
		UVO uvo = (UVO)session.getAttribute("UVO");
		stockForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stockForm.setUpdateTime(dateformat.format(date));
		boolean result = stockService.addStock(stockForm);
		if(!result) {
			throw new SQLException("库存单据信息添加失败！");
		}
		model.addAttribute("list", stockService.searchStockList());		
        return "manager/stock/stockList";
	}
	
	@RequestMapping(value = "initEditStock", method = RequestMethod.GET)
	public String initEditStock(Model model, StockForm stockForm) {
		log.info("修改库存单据信息初始化");
		StockForm result = stockService.getEditCommodity(stockForm);
    	model.addAttribute("stockForm", result);
		return "manager/stock/editStock";
	}
	
	@RequestMapping(value = "editStock", method = RequestMethod.POST)
	public String executeEditStock(Model model, HttpSession session, @Valid @ModelAttribute("stockForm") StockForm stockForm, BindingResult results) throws SQLException, IOException {
		log.info("修改库存单据信息");
		if (results.hasErrors()) {
	        return "manager/stock/editStock";			
		}
		UVO uvo = (UVO)session.getAttribute("UVO");
		stockForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stockForm.setUpdateTime(dateformat.format(date));
		boolean result = stockService.editStock(stockForm);
		if(!result) {
			throw new SQLException("库存单据信息更新失败！");
		}
		model.addAttribute("list", stockService.searchStockList());
        return "manager/stock/stockList";
	}
	
	@RequestMapping(value = "delStock", method = RequestMethod.GET)
	public String executeDelStock(Model model, StockForm stockForm) throws SQLException {
		log.info("删除库存单据信息");
		boolean result = stockService.delStock(stockForm);
		if(!result) {
			throw new SQLException("商品信息删除失败！");
		}
		model.addAttribute("list", stockService.searchStockList());
		return "manager/stock/stockList";
	}
}
