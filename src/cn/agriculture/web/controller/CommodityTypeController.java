package cn.agriculture.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.common.component.ItemListComponent;
import cn.agriculture.web.form.CommodityTypeForm;
import cn.agriculture.web.service.CommodityTypeService;

@Slf4j
@Controller("CommodityTypeController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class CommodityTypeController {
	@Autowired
	CommodityTypeService commodityTypeService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
	@Autowired
	private Environment env;
    @RequestMapping(value = "initCommodityType", method = RequestMethod.GET)
    public String initCommodityType(Model model,CommodityTypeForm commodityTypeForm) {
    	log.info("商品类型列表初始化");
    	model.addAttribute("list", commodityTypeService.searchCommodityTypeList());
        return "manager/commodityType/commodityTypeList";
    }
    @RequestMapping(value = "initEditCommodityType", method = RequestMethod.GET)
    public String initEditCommodityType(Model model, CommodityTypeForm commodityTypeForm) {
	log.info("修改商品类型信息初始化");
	model.addAttribute("commodityTypeForm", commodityTypeService.searchCommodityType(commodityTypeForm));
	return "manager/commodityType/editCommodityType";
    }
	@RequestMapping(value = "editCommodityType", method = RequestMethod.POST)
	public String editCommodityType(Model model, HttpSession session, @Valid @ModelAttribute("commodityTypeForm") CommodityTypeForm commodityTypeForm, BindingResult results) throws SQLException, IOException {
		log.info("修改商品类型信息");
		if(results.hasErrors())
		{
			log.info("内容出错");
			model.addAttribute("commodityTypeForm", commodityTypeForm);
			return"manager/commodityType/editCommodityType";
		}
		CommodityTypeForm result=commodityTypeService.searchCommodityTypeName(commodityTypeForm);
		if(result!=null){
			model.addAttribute("message", "该商品类型已存在");
			return"manager/commodityType/editCommodityType";
		}else{
		model.addAttribute("commodityTypeForm", commodityTypeService.updateCommodityType(commodityTypeForm));
		model.addAttribute("list", commodityTypeService.searchCommodityTypeList());}
        return "manager/commodityType/commodityTypeList";
	}
    @RequestMapping(value = "initAddCommodityType", method = RequestMethod.GET)
    public String initCommodity(Model model,CommodityTypeForm commodityTypeForm) {
    	log.info("添加商品类型信息初始化");
        return "manager/commodityType/addCommodityType";
    }
	@RequestMapping(value = "addCommodityType", method = RequestMethod.POST)
	public String addCommodityType(Model model, HttpSession session, @Valid @ModelAttribute("commodityTypeForm") CommodityTypeForm commodityTypeForm, BindingResult results) throws SQLException, IOException {
		log.info("添加商品类型信息");
		if(results.hasErrors())
		{
			log.info("内容出错");
			model.addAttribute("commodityTypeForm", commodityTypeForm);
			return"manager/commodityType/addCommodityType";
		}
		CommodityTypeForm result=commodityTypeService.searchCommodityTypeName(commodityTypeForm);
		if(result!=null){
			model.addAttribute("message", "该商品类型已存在");
			return"manager/commodityType/addCommodityType";
		}else{
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		commodityTypeForm.setUpdateTime(dateformat.format(date));
		model.addAttribute("commodityTypeForm", commodityTypeService.addCommodityType(commodityTypeForm));
		model.addAttribute("list", commodityTypeService.searchCommodityTypeList());}
        return "manager/commodityType/commodityTypeList";
	}
	@RequestMapping(value = "delCommodityType", method = RequestMethod.GET)
	public String delCommodityType(Model model, HttpSession session, @Valid @ModelAttribute("CommodityTypeForm") CommodityTypeForm commodityTypeForm, BindingResult results) throws SQLException, IOException {
		log.info("删除商品类型");
		model.addAttribute("commodityTypeForm", commodityTypeService.delCommodityType(commodityTypeForm));
		model.addAttribute("list", commodityTypeService.searchCommodityTypeList());
        return "manager/commodityType/commodityTypeList";
	}
}
