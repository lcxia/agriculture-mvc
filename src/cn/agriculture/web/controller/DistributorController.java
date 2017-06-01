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
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.DistributorService;

@Slf4j
@Controller("DistributorController")
@RequestMapping("/")
public class DistributorController {

	@Autowired
	DistributorService distributorService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initDistributor", method = RequestMethod.GET)
    public String initDistributor(Model model) {
    	log.info("分销商列表初始化");
    	model.addAttribute("list", distributorService.searchDistributorList());
        return "manager/distributor/distributorList";
    }
    
    @RequestMapping(value = "initAddDistributor", method = RequestMethod.GET)
    public String initAddDistributor(Model model) {
    	log.info("追加分销商初始化");
    	List<Item> provinceList = itemListComponent.getProvinceList();
    	model.addAttribute("provinceList", provinceList);
    	DistributorForm distributorForm = new DistributorForm();
    	model.addAttribute("distributorForm", distributorForm);
    	return "manager/distributor/addDistributor";
    }
    
	@RequestMapping(value = "addDistributor", method = RequestMethod.POST)
	public String executeAddDistributor(Model model, HttpSession session, @Valid @ModelAttribute("distributorForm") DistributorForm distributorForm, BindingResult results) throws SQLException {
		log.info("添加分销商信息");
		if(results.hasErrors()){
			List<Item> provinceList = itemListComponent.getProvinceList();
	    	model.addAttribute("provinceList", provinceList);
	    	model.addAttribute("distributorForm", distributorForm);
			return "manager/distributor/addDistributor";
		}
		if(distributorForm.getDistributorId().length() > 4 && "Guest".equals(distributorForm.getDistributorId().substring(0, 5))) {
			log.info("ID验证出错");
			model.addAttribute("message", "Guest是系统预留关键字，请避免使用！");
			List<Item> provinceList = itemListComponent.getProvinceList();
	    	model.addAttribute("provinceList", provinceList);
	    	model.addAttribute("distributorForm", distributorForm);
	    	return "manager/distributor/addDistributor";
		}
		UVO uvo = (UVO)session.getAttribute("UVO");
		distributorForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		distributorForm.setUpdateTime(dateformat.format(date));

		boolean result = distributorService.addDistributor(distributorForm);
		if(!result) {
			throw new SQLException("分销商信息添加失败！");
		}
		model.addAttribute("list", distributorService.searchDistributorList());
        return "manager/distributor/distributorList";
	}
	
	@RequestMapping(value = "addDistributorSelf", method = RequestMethod.POST)
	public String executeAddDistributorSelf(Model model, @Valid @ModelAttribute("distributorForm") DistributorForm distributorForm, BindingResult results) throws SQLException {
		log.info("自助添加分销商信息");
		if(results.hasErrors()) {
			log.info("内容验证出错");
			return "manager/distributor/addDistributorSelf";
		}
		if(distributorForm.getDistributorId().length() > 4 && "Guest".equals(distributorForm.getDistributorId().substring(0, 5))) {
			log.info("ID验证出错");
			model.addAttribute("message", "Guest是系统预留关键字，请避免使用！");
	    	model.addAttribute("distributorForm", distributorForm);
	    	return "manager/distributor/addDistributorSelf";
		}
		distributorForm.setUpdateUser(distributorForm.getDistributorId());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		distributorForm.setUpdateTime(dateformat.format(date));

		if (!distributorForm.getPassword().equals(distributorForm.getPasswordConfirm())) {
			log.info("密码验证出错");
			model.addAttribute("message", "密码和密码确认不一致！");
	    	model.addAttribute("distributorForm", distributorForm);
	    	return "manager/distributor/addDistributorSelf";
		}
		distributorForm.setPriceIncrement("0.2");
		distributorForm.setProvinceId("0000000003");
		distributorForm.setCityId("0000000003");
		boolean result = distributorService.addDistributor(distributorForm);
		if(!result) {
			throw new SQLException("分销商信息添加失败！");
		}
		model.addAttribute("list", distributorService.searchDistributorList());
        return "redirect:init";
	}
	
	@RequestMapping(value = "initEditDistributor", method = RequestMethod.GET)
	public String initEditDistributor(Model model, DistributorForm distributorForm) {
		log.info("修改分销商信息初始化");
		DistributorForm result = distributorService.searchDistributor(distributorForm);
		model.addAttribute("distributorForm", result);
		List<Item> provinceList = itemListComponent.getProvinceList();
		List<Item> cityList = itemListComponent.getCityList(result.getProvinceId());
    	model.addAttribute("provinceList", provinceList);
    	model.addAttribute("cityList", cityList);
		return "manager/distributor/editDistributor";
	}
	
	@RequestMapping(value = "editDistributor", method = RequestMethod.POST)
	public String executeEditDistributor(Model model, HttpSession session, @Valid @ModelAttribute("distributorForm") DistributorForm distributorForm, BindingResult results) throws SQLException {
		if (results.hasErrors()){
    		log.info("内容验证出错");
    		List<Item> provinceList = itemListComponent.getProvinceList();
    		model.addAttribute("provinceList", provinceList);
    		List<Item> cityList = itemListComponent.getCityList(distributorForm.getProvinceId());
    		model.addAttribute("cityList", cityList);
    		 return "manager/distributor/editDistributor";
    	}
		log.info("修改分销商信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		distributorForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		distributorForm.setUpdateTime(dateformat.format(date));
		boolean result = distributorService.editDistributor(distributorForm);
		if(!result) {
			throw new SQLException("分销商信息更新失败！");
		}
		model.addAttribute("list", distributorService.searchDistributorList());
		return "manager/distributor/distributorList";
	}
	
	@RequestMapping(value = "delDistributor", method = RequestMethod.GET)
	public String executeDelDistributor(DistributorForm distributorForm, Model model) throws SQLException {
		log.info("删除分销商信息");
		boolean result = distributorService.delDistributor(distributorForm);
		if(!result) {
			throw new SQLException("分销商信息删除失败！");
		}
		model.addAttribute("list", distributorService.searchDistributorList());
		return "manager/distributor/distributorList";
	}
	
	@RequestMapping(value = "initEditDistributorPassword", method = RequestMethod.GET)
	public String initEditDistributorPassword(Model model) {
		log.info("修改分销商密码初始化");
		
		DistributorForm distributorForm = new DistributorForm();
		model.addAttribute("distributorForm", distributorForm);
		return "manager/distributor/editDistributorPassword";
	}
	
	@RequestMapping(value = "editDistributorPassword", method = RequestMethod.POST)
	public String executeEditDistributorPassword(Model model, HttpSession session, @Valid @ModelAttribute("distributorForm") DistributorForm distributorForm, BindingResult results) throws SQLException {
		if (results.hasErrors()){
    		log.info("内容验证出错");
    		List<Item> provinceList = itemListComponent.getProvinceList();
        	model.addAttribute("provinceList", provinceList);
    		 return "manager/distributor/editDistributorPassword";
    	}
		log.info("修改分销商密码");
		UVO uvo = (UVO)session.getAttribute("UVO");
		if(!uvo.getPassword().equals(distributorForm.getPassword())) {
			model.addAttribute("message", "原密码错误！");
			return "manager/distributor/editDistributorPassword";
		}
		if(!distributorForm.getPasswordNew().equals(distributorForm.getPasswordConfirm())) {
			model.addAttribute("message", "新密码与密码确认不一致！");
			return "manager/distributor/editDistributorPassword";
		}
		distributorForm.setDistributorId(uvo.getUserId());
		distributorForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		distributorForm.setUpdateTime(dateformat.format(date));
		boolean result = distributorService.editDistributorPassword(distributorForm);
		if(!result) {
			throw new SQLException("分销商密码更新失败！");
		}
		uvo.setPassword(distributorForm.getPasswordNew());
		session.setAttribute("UVO", uvo);
		return "manager/menu";
	}
	
}
