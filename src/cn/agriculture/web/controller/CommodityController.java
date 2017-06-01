package cn.agriculture.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.agriculture.common.component.ItemListComponent;
import cn.agriculture.web.form.CommodityForm;
import cn.agriculture.web.form.Item;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CommodityService;

@Slf4j
@Controller("CommodityController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class CommodityController {

	@Autowired
	CommodityService commodityService;
	
	@Autowired
	ItemListComponent itemListComponent;
	
	@Autowired
	private Environment env;
	
    @RequestMapping(value = "initCommodity", method = RequestMethod.GET)
    public String initCommodity(Model model) {
    	log.info("商品列表初始化");
    	model.addAttribute("list", commodityService.searchCommodityList());
        return "manager/commodity/commodityList";
    }
    
    @RequestMapping(value = "initAddCommodity", method = RequestMethod.GET)
    public String initAddCommodity(Model model) {
    	log.info("追加商品初始化");
    	List<Item> commodityType = itemListComponent.getCommodityType();
    	model.addAttribute("commodityType", commodityType);
    	List<Item> supplierList = itemListComponent.getSupplierList();
    	model.addAttribute("supplierList", supplierList);
    	CommodityForm commodityForm = new CommodityForm();
    	model.addAttribute("commodityForm", commodityForm);
    	return "manager/commodity/addCommodity";
    }
    
	@RequestMapping(value = "addCommodity", method = RequestMethod.POST)
	public String executeAddCommodity(Model model, @RequestParam(value = "file", required = false) MultipartFile file
			, @RequestParam(value = "file1", required = false) MultipartFile file1
			, @RequestParam(value = "file2", required = false) MultipartFile file2
			, @RequestParam(value = "file3", required = false) MultipartFile file3
			, @RequestParam(value = "file4", required = false) MultipartFile file4
			, @RequestParam(value = "file5", required = false) MultipartFile file5
			, @RequestParam(value = "file6", required = false) MultipartFile file6,
			@RequestParam(value = "attachments", required = false) MultipartFile attachments, HttpSession session, @Valid @ModelAttribute("commodityForm") CommodityForm commodityForm, BindingResult results) throws SQLException, IOException {
		log.info("添加商品信息");
		if(results.hasErrors()){
			List<Item> commodityType = itemListComponent.getCommodityType();
	    	model.addAttribute("commodityType", commodityType);
			List<Item> supplierList = itemListComponent.getSupplierList();
	    	model.addAttribute("supplierList", supplierList);
			return "manager/commodity/addCommodity";}
		UVO uvo = (UVO)session.getAttribute("UVO");
		commodityForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		commodityForm.setUpdateTime(dateformat.format(date));
		commodityForm.setPicture(file.getBytes());
		commodityForm.setPicture1(file1.getBytes());
		commodityForm.setPicture2(file2.getBytes());
		commodityForm.setPicture3(file3.getBytes());
		commodityForm.setPicture4(file4.getBytes());
		commodityForm.setPicture5(file5.getBytes());
		commodityForm.setPicture6(file6.getBytes());
		boolean result = commodityService.addCommodity(commodityForm);
		if(!result) {
			throw new SQLException("商品信息添加失败！");
		}
	
		model.addAttribute("list", commodityService.searchCommodityList());
		if (!attachments.isEmpty()) {
    		String suffix = attachments.getOriginalFilename().substring(attachments.getOriginalFilename().lastIndexOf("."));
        	File targetFile = new File(env.getProperty("upload.file.path"), commodityForm.getCommodityId() + suffix);
        	if(!targetFile.exists()){
                targetFile.mkdirs();
            }
        	attachments.transferTo(targetFile);
    	}
        return "manager/commodity/commodityList";
	}
	
	@RequestMapping(value = "initEditCommodity", method = RequestMethod.GET)
	public String initEditCommodity(Model model, CommodityForm commodityForm) {
		log.info("修改商品信息初始化");
		CommodityForm result = commodityService.searchCommodity(commodityForm);
		model.addAttribute("commodityForm", result);
		List<Item> commodityType = itemListComponent.getCommodityType();
    	model.addAttribute("commodityType", commodityType);
		List<Item> supplierList = itemListComponent.getSupplierList();
    	model.addAttribute("supplierList", supplierList);
    	List<Item> brandList = itemListComponent.getBrandList(result.getSupplierId());
    	model.addAttribute("brandList", brandList);
		return "manager/commodity/editCommodity";
	}
	
	@RequestMapping(value = "editCommodity", method = RequestMethod.POST)
	public String executeEditCommodity(Model model, @RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "file1", required = false) MultipartFile file1,@RequestParam(value = "file2", required = false) MultipartFile file2,@RequestParam(value = "file3", required = false) MultipartFile file3,@RequestParam(value = "file4", required = false) MultipartFile file4,@RequestParam(value = "file5", required = false) MultipartFile file5,@RequestParam(value = "file6", required = false) MultipartFile file6, @RequestParam(value = "attachments", required = false) MultipartFile attachments, HttpSession session, @Valid @ModelAttribute("commodityForm") CommodityForm commodityForm, BindingResult results) throws SQLException, IOException {
		log.info("修改商品信息");
		if(results.hasErrors()){
			List<Item> commodityType = itemListComponent.getCommodityType();
	    	model.addAttribute("commodityType", commodityType);
			List<Item> supplierList = itemListComponent.getSupplierList();
	    	model.addAttribute("supplierList", supplierList);
	    	List<Item> brandList = itemListComponent.getBrandList(commodityForm.getSupplierId());
	    	model.addAttribute("brandList", brandList);
			return "manager/commodity/editCommodity";}
		UVO uvo = (UVO)session.getAttribute("UVO");
		commodityForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		commodityForm.setUpdateTime(dateformat.format(date));
		commodityForm.setPicture(file.getBytes());
		commodityForm.setPicture1(file1.getBytes());
		commodityForm.setPicture2(file2.getBytes());
		commodityForm.setPicture3(file3.getBytes());
		commodityForm.setPicture4(file4.getBytes());
		commodityForm.setPicture5(file5.getBytes());
		commodityForm.setPicture6(file6.getBytes());
		CommodityForm frm1=commodityService.searchCommodity(commodityForm);
		commodityForm.setPictureId(frm1.getPictureId());
		commodityForm.setPictureId1(frm1.getPictureId1());
		commodityForm.setPictureId2(frm1.getPictureId2());
		commodityForm.setPictureId3(frm1.getPictureId3());
		commodityForm.setPictureId4(frm1.getPictureId4());
		commodityForm.setPictureId5(frm1.getPictureId5());
		commodityForm.setPictureId6(frm1.getPictureId6());
		boolean result = commodityService.editCommodity(commodityForm);
		if(!result) {
			throw new SQLException("商品信息更新失败！");
		}
    	model.addAttribute("list", commodityService.searchCommodityList());
    	if (!attachments.isEmpty()) {
    		String suffix = attachments.getOriginalFilename().substring(attachments.getOriginalFilename().lastIndexOf("."));
        	File targetFile = new File(env.getProperty("upload.file.path"), commodityForm.getCommodityId() + suffix);
        	if(!targetFile.exists()){
                targetFile.mkdirs();
            }
        	attachments.transferTo(targetFile);
    	}
    	
        return "manager/commodity/commodityList";
	}
	
	@RequestMapping(value = "delCommodity", method = RequestMethod.GET)
	public String executeDelCommodity(Model model, CommodityForm commodityForm) throws SQLException {
		log.info("删除商品信息");
		boolean result = commodityService.delCommodity(commodityForm);
		if(!result) {
			throw new SQLException("商品信息删除失败！");
		}
		model.addAttribute("list", commodityService.searchCommodityList());
		File targetFile = new File(env.getProperty("upload.file.path"), commodityForm.getCommodityId() + ".zip");
		targetFile.delete();
		return "manager/commodity/commodityList";
	}
	
    @RequestMapping(value = "getBrand", method = RequestMethod.POST)
    public @ResponseBody Map<String, List<Map<String, String>>> getBrand(String supplierId) {
    	List<Item> brandList = itemListComponent.getBrandList(supplierId);
    	List<Map<String, String>> list= new ArrayList<>();
    	for(Item item : brandList) {
    		Map<String, String> brandMap = new HashMap<>();
    		brandMap.put("value", item.getValue());
    		brandMap.put("label", item.getLabel());
    		list.add(brandMap);
    	}
    	Map<String, List<Map<String, String>>> josnMap = new HashMap<>();
    	josnMap.put("brands", list);
    	return josnMap;
    }
    
	@RequestMapping(value = "showImage", method = RequestMethod.GET)
	public void showImage(HttpServletResponse response, CommodityForm commodityForm) throws IOException {
		log.info("取得商品图片");
		response.setContentType("image/jpg");
		OutputStream stream = response.getOutputStream();
		try {
			stream.write(commodityService.getPicture(commodityForm));
			stream.flush();
			
		} catch (Exception e) {
			log.info(commodityForm.getPictureId() + ",该图片不存在，需要补充！");
		} finally {
			stream.close();
		}
	}

	@RequestMapping(value = "download", method = RequestMethod.GET)  
	public void download(HttpServletResponse response, CommodityForm commodityForm) throws IOException {  
		log.info("文件下载");
	    OutputStream stream = response.getOutputStream();  
	    try {  
	    	response.reset();  
	    	response.setHeader("Content-Disposition", "attachment; filename=" + commodityForm.getCommodityId() + ".zip");  
	    	response.setContentType("application/octet-stream; charset=utf-8");
	        File targetFile = new File(env.getProperty("upload.file.path"), commodityForm.getCommodityId() + ".zip");
	        stream.write(FileUtils.readFileToByteArray(targetFile));  
	        stream.flush();  
	    } finally {  
	        if (stream != null) {  
	        	stream.close();  
	        }  
	    }  
	} 
}
