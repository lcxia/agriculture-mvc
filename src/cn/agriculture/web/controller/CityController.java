package cn.agriculture.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import cn.agriculture.web.form.CityForm;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.service.CityService;

@Slf4j
@Controller("CityController")
@RequestMapping("/")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initAddCity", method = RequestMethod.GET)
    public String initCommodity(Model model) {
    	log.info("添加城市初始化");
    	List<Item> provinceList = itemListComponent.getProvinceList();
    	model.addAttribute("provinceList", provinceList);
    	CityForm cityForm = new CityForm();
    	model.addAttribute("cityForm", cityForm);
        return "manager/city/addCity";
    }
    
	@RequestMapping(value = "addCity", method = RequestMethod.POST)
	public String executeAddCity(Model model, @Valid @ModelAttribute("cityForm") CityForm cityForm, BindingResult results) throws SQLException, IOException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			List<Item> provinceList = itemListComponent.getProvinceList();
	    	model.addAttribute("provinceList", provinceList);
			return "manager/city/addCity";}
		log.info("添加城市");
		boolean result = cityService.addCity(cityForm);
		if(!result) {
			throw new SQLException("城市添加失败！");
		}
		List<Item> provinceList = itemListComponent.getProvinceList();
    	model.addAttribute("provinceList", provinceList);
    	cityForm.setCityName(null);
    	model.addAttribute("cityForm", cityForm);
        return "manager/city/addCity";
	}
}
