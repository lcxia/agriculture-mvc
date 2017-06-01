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
import cn.agriculture.web.form.BrandForm;
import cn.agriculture.web.form.SupplierForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.BrandService;
import cn.agriculture.web.service.SupplierService;

@Slf4j
@Controller("BrandController")
@RequestMapping("/")
public class BrandController {

	@Autowired
	BrandService brandService;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
    @RequestMapping(value = "initBrand", method = RequestMethod.GET)
    public String init(Model model, SupplierForm supplierForm,BrandForm brandForm) {
    	log.info("品牌列表初始化");
    	List<SupplierForm> list = supplierService.searchSupplier(supplierForm);
    	SupplierForm result = list.get(0);
    	result.setProvince(itemListComponent.getProvinceLabel(result.getProvince()));
    	result.setCity(itemListComponent.getCityLabel(result.getCity()));
		model.addAttribute("supplierForm", result);
		if(brandForm.getSupplierId()==null)
    	{BrandForm param = new BrandForm();
    	param.setSupplierId(supplierForm.getSupplierId());
    	model.addAttribute("list", brandService.searchBrand(param));
        return "manager/brand/brandList";}
		else 
		{
	    	model.addAttribute("list", brandService.searchBrand(brandForm));
	        return "manager/brand/brandList";
		}
    }
    
    @RequestMapping(value = "initAddBrand", method = RequestMethod.GET)
    public String initAddBrand(Model model, SupplierForm supplierForm) {
    	log.info("追加品牌信息初始化");
    	BrandForm brandForm = new BrandForm();
    	brandForm.setSupplierId(supplierForm.getSupplierId());
    	model.addAttribute("brandForm", brandForm);
        return "manager/brand/addBrand";
    }
    
    @RequestMapping(value = "addBrand", method = RequestMethod.POST)
    public String executeAddBrand(Model model, HttpSession session, @Valid @ModelAttribute("brandForm") BrandForm brandForm, BindingResult results) throws SQLException {
    	log.info("追加品牌信息");
    	if(results.hasErrors())
    	{
    		return "manager/brand/addBrand";
    	}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	brandForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		brandForm.setUpdateTime(dateformat.format(date));

		boolean result = brandService.addBrand(brandForm);
		if(!result) {
			throw new SQLException("品牌信息追加失败！");
		}
		SupplierForm supplierForm = new SupplierForm();
		supplierForm.setSupplierId(brandForm.getSupplierId());
		List<SupplierForm> list = supplierService.searchSupplier(supplierForm);
    	SupplierForm resultSupplier = list.get(0);
    	resultSupplier.setProvince(itemListComponent.getProvinceLabel(resultSupplier.getProvince()));
    	resultSupplier.setCity(itemListComponent.getCityLabel(resultSupplier.getCity()));
		model.addAttribute("supplierForm", resultSupplier);
    	BrandForm param = new BrandForm();
    	param.setSupplierId(brandForm.getSupplierId());
    	model.addAttribute("list", brandService.searchBrand(param));
        return "manager/brand/brandList";
    }
    
	@RequestMapping(value = "initEditBrand", method = RequestMethod.GET)
	public String initEditBrand(Model model, BrandForm brandForm) {
		log.info("修改品牌信息初始化");
		List<BrandForm> list = brandService.searchBrand(brandForm);
		model.addAttribute("brandForm", list.get(0));
		return "manager/brand/editBrand";
	}
	
	@RequestMapping(value = "editBrand", method = RequestMethod.POST)
	public String executeEditBrand(Model model, HttpSession session, @Valid @ModelAttribute("brandForm") BrandForm brandForm, BindingResult results) throws SQLException {
		log.info("修改品牌信息");
		if(results.hasErrors()){
			return "manager/brand/editBrand";}
		UVO uvo = (UVO)session.getAttribute("UVO");
		brandForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		brandForm.setUpdateTime(dateformat.format(date));
		boolean result = brandService.editBrand(brandForm);
		if(!result) {
			throw new SQLException("品牌信息更新失败！");
		}
		SupplierForm supplierForm = new SupplierForm();
		supplierForm.setSupplierId(brandForm.getSupplierId());
		List<SupplierForm> list = supplierService.searchSupplier(supplierForm);
    	SupplierForm resultSupplier = list.get(0);
    	resultSupplier.setProvince(itemListComponent.getProvinceLabel(resultSupplier.getProvince()));
    	resultSupplier.setCity(itemListComponent.getCityLabel(resultSupplier.getCity()));
		model.addAttribute("supplierForm", resultSupplier);
    	BrandForm param = new BrandForm();
    	param.setSupplierId(brandForm.getSupplierId());
    	model.addAttribute("list", brandService.searchBrand(param));
        return "manager/brand/brandList";
	}
	
	@RequestMapping(value = "delBrand", method = RequestMethod.GET)
	public String executeDelBrand(BrandForm brandForm, Model model) throws Exception {
		log.info("删除品牌信息");
		boolean result = brandService.delBrand(brandForm);
		if(!result) {
			throw new SQLException("品牌信息删除失败！");
		}
		SupplierForm supplierForm = new SupplierForm();
		supplierForm.setSupplierId(brandForm.getSupplierId());
		List<SupplierForm> list = supplierService.searchSupplier(supplierForm);
    	SupplierForm resultSupplier = list.get(0);
    	resultSupplier.setProvince(itemListComponent.getProvinceLabel(resultSupplier.getProvince()));
    	resultSupplier.setCity(itemListComponent.getCityLabel(resultSupplier.getCity()));
		model.addAttribute("supplierForm", resultSupplier);
    	BrandForm param = new BrandForm();
    	param.setSupplierId(brandForm.getSupplierId());
    	model.addAttribute("list", brandService.searchBrand(param));
        return "manager/brand/brandList";
	}
}
