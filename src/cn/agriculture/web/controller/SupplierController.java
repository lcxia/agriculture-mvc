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
import cn.agriculture.web.form.SupplierForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.SupplierService;

@Slf4j
@Controller("SupplierController")
@RequestMapping("/")
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	
	@Autowired
	ItemListComponent itemListComponent;

	
    @RequestMapping(value = "initSupplier", method = RequestMethod.GET)
    public String init(Model model) {
    	log.info("供应商列表初始化");
    	SupplierForm supplierForm = new SupplierForm();
    	model.addAttribute("list", supplierService.searchSupplier(supplierForm));
        return "manager/supplier/supplierList";
    }
    
    @RequestMapping(value = "initAddSupplier", method = RequestMethod.GET)
    public String initAddSupplier(Model model) {
    	log.info("追加供应商初始化");
    	List<Item> provinceList = itemListComponent.getProvinceList();
    	model.addAttribute("provinceList", provinceList);
    	SupplierForm supplierForm = new SupplierForm();
    	model.addAttribute("supplierForm", supplierForm);
    	return "manager/supplier/addSupplier";
    }
    
	@RequestMapping(value = "addSupplier", method = RequestMethod.POST)
	public String executeAddSupplier(Model model, HttpSession session, @Valid @ModelAttribute("supplierForm") SupplierForm supplierForm, BindingResult results) throws SQLException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
	    	List<Item> provinceList = itemListComponent.getProvinceList();
	    	model.addAttribute("provinceList", provinceList);
			model.addAttribute("message", "该画面所有项目都是必填项，请认真填写！");
			model.addAttribute("supplierForm", supplierForm);
			return "manager/supplier/addSupplier";
		}
		log.info("添加供应商信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		supplierForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		supplierForm.setUpdateTime(dateformat.format(date));

		boolean result = supplierService.addSupplier(supplierForm);
		if(!result) {
			throw new SQLException("供应商信息添加失败！");
		}
		SupplierForm param = new SupplierForm();
		model.addAttribute("list", supplierService.searchSupplier(param));
        return "manager/supplier/supplierList";
	}
    
	@RequestMapping(value = "initEditSupplier", method = RequestMethod.GET)
	public String initEditSupplier(Model model, SupplierForm supplierForm) {
		log.info("修改供应商信息初始化");
		List<SupplierForm> list = supplierService.searchSupplier(supplierForm);
		model.addAttribute("supplierForm", list.get(0));
		List<Item> provinceList = itemListComponent.getProvinceList();
    	model.addAttribute("provinceList", provinceList);
		List<Item> cityList = itemListComponent.getCityList(list.get(0).getProvince());
    	model.addAttribute("cityList", cityList);
		return "manager/supplier/editSupplier";
	}
	
	@RequestMapping(value = "editSupplier", method = RequestMethod.POST)
	public String executeEditSupplier(Model model, HttpSession session, @Valid @ModelAttribute("supplierForm") SupplierForm supplierForm, BindingResult results) throws SQLException {
		log.info("修改供应商信息");
		if (results.hasErrors()) {
			log.info("内容验证出错");
			List<Item> provinceList = itemListComponent.getProvinceList();
	    	model.addAttribute("provinceList", provinceList);
			List<Item> cityList = itemListComponent.getCityList(supplierForm.getProvince());
	    	model.addAttribute("cityList", cityList);
			return "manager/supplier/editSupplier";
		}
		UVO uvo = (UVO)session.getAttribute("UVO");
		supplierForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		supplierForm.setUpdateTime(dateformat.format(date));
		boolean result = supplierService.editSupplier(supplierForm);
		if(!result) {
			throw new SQLException("供应商信息更新失败！");
		}
		SupplierForm param = new SupplierForm();
		model.addAttribute("list", supplierService.searchSupplier(param));
		return "manager/supplier/supplierList";
	}
	
	@RequestMapping(value = "delSupplier", method = RequestMethod.GET)
	public String executeDelSupplier(SupplierForm supplierForm, Model model) throws SQLException {
		log.info("删除供应商信息");
		boolean result = supplierService.delSupplier(supplierForm);
		if(!result) {
			throw new SQLException("供应商信息删除失败！");
		}
		SupplierForm param = new SupplierForm();
		model.addAttribute("list", supplierService.searchSupplier(param));
		return "manager/supplier/supplierList";
	}
	
    @RequestMapping(value = "getCity", method = RequestMethod.POST)
    public @ResponseBody Map<String, List<Map<String, String>>> getCity(String provinceId) {
    	List<Item> cityList = itemListComponent.getCityList(provinceId);
    	List<Map<String, String>> list= new ArrayList<>();
    	for(Item item:cityList) {
    		Map<String, String> cityMap = new HashMap<>();
    		cityMap.put("value", item.getValue());
    		cityMap.put("label", item.getLabel());
    		list.add(cityMap);
    	}
    	Map<String, List<Map<String, String>>> josnMap = new HashMap<>();
    	josnMap.put("citys", list);
    	return josnMap;
    }
}

