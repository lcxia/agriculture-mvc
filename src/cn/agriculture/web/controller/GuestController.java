package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.common.util.MD5Util;
import cn.agriculture.web.form.CartForm;
import cn.agriculture.web.form.GoodsForm;
import cn.agriculture.web.form.GuestForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CartService;
import cn.agriculture.web.service.CommodityTypeService;
import cn.agriculture.web.service.GoodsService;
import cn.agriculture.web.service.GuestService;

@Slf4j
@Controller("GuestController")
@RequestMapping("/")
public class GuestController {

	@Autowired
	GuestService guestService;
	
	@Autowired
	CommodityTypeService commodityTypeService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	CartService cartService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session, Device device) {
    	log.info("系统初始化");
    	UVO uvo = new UVO();
    	session.setAttribute("UVO", uvo);   	
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	GoodsForm goodsForm = new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
		
		int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setCount(i+"F");
			commodityType.get(i-1).setCss("columnT"+" "+"columnT-"+(i%6+1));
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	model.addAttribute("list", goodsService.searchGoodsList(goodsForm));
    	GoodsForm goodsFormForId = new GoodsForm();
    	for(int i=0;i<commodityType.size();i++){
    		goodsFormForId.setCommodityTypeId(commodityType.get(i).getCommodityTypeId());
    		commodityType.get(i).setList(goodsService.searchGoodsListLimit(goodsFormForId));
    	}	
    	if(device.isNormal()) {
    		return "shop/index";
    	} else {
    		return "mobile/index";
    	}
        
    }
    

    @RequestMapping(value = "initIndex", method = RequestMethod.GET)
    public String index1(Model model, HttpSession session, Device device) {
    	log.info("系统初始化");
    	UVO uvo=(UVO) session.getAttribute("UVO");
    	  if (uvo == null) {
    			uvo = new UVO();
    			session.setAttribute("UVO", uvo);
    		}
    	GoodsForm goodsForm = new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
		
    	model.addAttribute("goodsForm", goodsForm);
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setCount(i+"F");
			commodityType.get(i-1).setCss("columnT"+" "+"columnT-"+(i%6+1));
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("commodityType",commodityType);
    	model.addAttribute("list", goodsService.searchGoodsList(goodsForm));
    	GoodsForm goodsFormForId = new GoodsForm();
    	for(int i=0;i<commodityType.size();i++){
    		goodsFormForId.setCommodityTypeId(commodityType.get(i).getCommodityTypeId());
    		commodityType.get(i).setList(goodsService.searchGoodsListLimit(goodsFormForId));
    	}
    	CartForm cartForm=new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	cartForm.setStatus("未付款");
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/index";
    	} else {
    		return "mobile/index";
    	}
        
    }
    
    @RequestMapping(value = "aboutUs", method = RequestMethod.GET)
    public String aboutUs(Model model, HttpSession session, Device device) {
    	UVO uvo=(UVO) session.getAttribute("UVO");
  	  if (uvo == null) {
  			uvo = new UVO();
  			session.setAttribute("UVO", uvo);
  		}
  	GoodsForm goodsForm = new GoodsForm();
	List<GoodsForm> commodityType = goodsService.getType();
	
	model.addAttribute("goodsForm", goodsForm);
	model.addAttribute("commodityType",commodityType);
	CartForm cartForm=new CartForm();
	cartForm.setGuestId(uvo.getGuestId());
	cartForm.setStatus("未付款");
	model.addAttribute("cartList", cartService.searchCartList(cartForm));	
	     if(device.isNormal()) {
    		return "shop/aboutUs";
    	} else {
    		return "mobile/aboutUs";
    	}
    }
    
    @RequestMapping(value = "contactUs", method = RequestMethod.GET)
    public String contactUs(Model model, HttpSession session, Device device) {
    	UVO uvo=(UVO) session.getAttribute("UVO");
  	  if (uvo == null) {
  			uvo = new UVO();
  			session.setAttribute("UVO", uvo);
  		}
   	GoodsForm goodsForm = new GoodsForm();
  	List<GoodsForm> commodityType = goodsService.getType();
  
  	model.addAttribute("goodsForm", goodsForm);
  	model.addAttribute("commodityType",commodityType);
  	CartForm cartForm=new CartForm();
	cartForm.setGuestId(uvo.getGuestId());
	cartForm.setStatus("未付款");
	model.addAttribute("cartList", cartService.searchCartList(cartForm));
  	     if(device.isNormal()) {
    		return "shop/contactUs";
    	} else {
    		return "mobile/contactUs";
    	}
    }
    
    @RequestMapping(value = "initGuestLogin", method = RequestMethod.GET)
    public String initGuestLogin(Model model, Device device) {
    	log.info("客户登录界面初始化");
    	GoodsForm goodsForm = new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
 
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	List<CartForm> cartList = new ArrayList<>();
    	model.addAttribute("cartList", cartList);
    	GuestForm guestForm = new GuestForm();
    	model.addAttribute("guestForm", guestForm);
    	if(device.isNormal()) {
    		return "shop/login";
    	} else {
    		return "mobile/login";
    	}
    }
    
	@RequestMapping(value = "guestLogin", method = RequestMethod.POST)
    public String guestLogin(Model model, HttpSession session, @Valid @ModelAttribute("guestForm")GuestForm guestForm,BindingResult result1, Device device) { 
		log.info("客户登录，验证客户信息，成功后进入系统");
		if(result1.hasErrors())
		{
			if(device.isNormal()) {
	    		return "shop/login";
	    	} else {
	    		return "mobile/login";
	    	}
		}
		GuestForm result = guestService.searchGuest(guestForm);
		GoodsForm goodsForm = new GoodsForm();
		List<GoodsForm> commodityType = goodsService.getType();
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
		if(result != null) {
			UVO uvo = new UVO();
			uvo.setGuestId(result.getGuestId());
			uvo.setGuestName(result.getGuestName());
			uvo.setPassword(guestForm.getPassword());
			uvo.setGender(result.getGender());
			uvo.setEmail(result.getEmail());
			uvo.setMobile(result.getMobile());
			uvo.setQq(result.getQq());
			uvo.setPhone(result.getPhone());
			uvo.setZip(result.getZip());
			session.setAttribute("UVO", uvo);
			GoodsForm goodsForm1 = new GoodsForm();
	    	List<GoodsForm> commodityType1 = goodsService.getType();
	    	model.addAttribute("goodsForm", goodsForm1);
	    	int sum=commodityType1.size();
			for(int i=1;i<=sum;i++){
				commodityType1.get(i-1).setCount(i+"F");
				commodityType1.get(i-1).setCss("columnT"+" "+"columnT-"+(i%6+1));
				commodityType1.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
			}
	    	model.addAttribute("commodityType",commodityType1);
	    	model.addAttribute("list", goodsService.searchGoodsList(goodsForm1));
	    	GoodsForm goodsFormForId = new GoodsForm();
	    	for(int i=0;i<commodityType1.size();i++){
	    		goodsFormForId.setCommodityTypeId(commodityType1.get(i).getCommodityTypeId());
	    		commodityType1.get(i).setList(goodsService.searchGoodsListLimit(goodsFormForId));
	    	}
	    	CartForm cartForm = new CartForm();
	    	cartForm.setGuestId(uvo.getGuestId());
	    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
	    	if(device.isNormal()) {
	    		return "shop/index";
	    	} else {
	    		return "mobile/index";
	    	}
		} else {
			model.addAttribute("message", "用户名或密码错误！");
	    	List<CartForm> cartList = new ArrayList<>();
	    	model.addAttribute("cartList", cartList);
			if(device.isNormal()) {
	    		return "shop/login";
	    	} else {
	    		return "mobile/login";
	    	}
		}
	}
    
    @RequestMapping(value = "initGuestRegister", method = RequestMethod.GET)
    public String initGuestRegister(Model model, Device device) {
    	log.info("客户注册界面初始化");
    	GoodsForm goodsForm = new GoodsForm();
		List<GoodsForm> commodityType = goodsService.getType();
 
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
    	List<CartForm> cartList = new ArrayList<>();
    	model.addAttribute("cartList", cartList);
    	GuestForm guestForm = new GuestForm();
    	model.addAttribute("guestForm", guestForm);
    	if(device.isNormal()) {
    		return "shop/register-1";
    	} else {
    		return "mobile/register-1";
    	}
    }
    
	@RequestMapping(value = "addGuest", method = RequestMethod.POST)
	public String executeAddGuest(Model model,HttpSession session, @Valid @ModelAttribute("guestForm") GuestForm guestForm, BindingResult results, Device device) throws SQLException {
		
		model.addAttribute("guestForm", guestForm);
		GoodsForm goodsForm = new GoodsForm();
//    	goodsForm.setType("粮食");
//    	model.addAttribute("commodityType", goodsService.getType(goodsForm));
//    	model.addAttribute("goodsForm", goodsForm);
		List<GoodsForm> commodityType = goodsService.getType();
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
    	String passWord=guestForm.getPassword();
    	String passWordConfirm=guestForm.getPasswordConfirm();
    	if(passWord.equals(passWordConfirm)){
    		
    	}else{
    		model.addAttribute("message1", "密码和确认密码必须一致！");
    	}
		if (results.hasErrors()) {
			log.info("内容验证出错");
	    	List<CartForm> cartList = new ArrayList<>();
	    	model.addAttribute("cartList", cartList);
			if(device.isNormal()) {
	    		return "shop/register-1";
	    	} else {
	    		return "mobile/register-1";
	    	}
		}
		if(guestForm.getGuestId().length() > 4 && "Guest".equals(guestForm.getGuestId().substring(0, 5))) {
			log.info("ID验证出错");
			model.addAttribute("message", "Guest是系统预留关键字，请避免使用！");
	    	List<CartForm> cartList = new ArrayList<>();
	    	model.addAttribute("cartList", cartList);
			if(device.isNormal()) {
	    		return "shop/register-1";
	    	} else {
	    		return "mobile/register-1";
	    	}
		}
		if (!guestForm.getPassword().equals(guestForm.getPasswordConfirm())) {
			log.info("密码验证出错");
			model.addAttribute("message1", "密码和密码确认必须一致！");
	    	List<CartForm> cartList = new ArrayList<>();
	    	model.addAttribute("cartList", cartList);
			if(device.isNormal()) {
	    		return "shop/register-1";
	    	} else {
	    		return "mobile/register-1";
	    	}
		}
		log.info("添加客户信息");
		guestForm.setUpdateUser(guestForm.getGuestId());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		guestForm.setUpdateTime(dateformat.format(date));
		boolean result = guestService.addGuest(guestForm);
		if(!result) {
			//throw new SQLException("客户信息添加失败！");
			model.addAttribute("message", "该用户ID已被占用，请更换用户ID！");
	    	List<CartForm> cartList = new ArrayList<>();
	    	model.addAttribute("cartList", cartList);
			if(device.isNormal()) {
	    		return "shop/register-1";
	    	} else {
	    		return "mobile/register-1";
	    	}
		}
		UVO uvo = new UVO();
		uvo.setGuestId(guestForm.getGuestId());
		uvo.setGuestName(guestForm.getGuestName());
		uvo.setPassword(guestForm.getPassword());
		uvo.setGender(guestForm.getGender());
		uvo.setEmail(guestForm.getEmail());
		uvo.setMobile(guestForm.getMobile());
		uvo.setQq(guestForm.getQq());
		uvo.setPhone(guestForm.getPhone());
		uvo.setZip(guestForm.getZip());
		session.setAttribute("UVO", uvo);
//		GoodsForm goodsForm = new GoodsForm();
//    	goodsForm.setType("粮食");
//    	model.addAttribute("commodityType", goodsService.getType(goodsForm));
//    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("list", goodsService.searchGoodsList(goodsForm));
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/register-2";
    	} else {
    		return "mobile/register-2";
    	}
	}
	
    @RequestMapping(value = "initEditGuest", method = RequestMethod.GET)
    public String initEditGuest(Model model, HttpSession session, @Valid @ModelAttribute("guestForm") GuestForm guestForm, Device device) {
    	log.info("修改客户信息初始化");
    	GoodsForm goodsForm = new GoodsForm();
//    	goodsForm.setType("粮食");
//    	model.addAttribute("commodityType", goodsService.getType(goodsForm));
//    	model.addAttribute("goodsForm", goodsForm);
    	List<GoodsForm> commodityType = goodsService.getType();
 
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
    	UVO uvo = (UVO)session.getAttribute("UVO");
        //GuestForm guestForm = new GuestForm();
    	guestForm.setGuestId(uvo.getGuestId());
    	guestForm.setPassword(uvo.getPassword());
    	GuestForm result = guestService.searchGuest(guestForm);
    	guestForm.setEmail(result.getEmail());
		guestForm.setGender(result.getGender());
		guestForm.setGuestId(result.getGuestId());
		guestForm.setGuestName(result.getGuestName());
		guestForm.setMobile(result.getMobile());
		guestForm.setPassword(MD5Util.getMD5(result.getPassword()));
		guestForm.setPhone(result.getPhone());
		guestForm.setQq(result.getQq());
		guestForm.setUpdateTime(result.getUpdateTime());
		guestForm.setUpdateUser(result.getUpdateUser());
		guestForm.setZip(result.getZip());
		model.addAttribute("guestForm", guestForm);
		CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
		if(device.isNormal()) {
    		return "shop/editGuest";
    	} else {
    		return "mobile/editGuest";
    	}
    }
    
	@RequestMapping(value = "editGuest", method = RequestMethod.POST)
	public String executeEditGuest(Model model, HttpSession session, @Valid @ModelAttribute("guestForm") GuestForm guestForm, BindingResult results, Device device) throws SQLException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			UVO uvo = (UVO)session.getAttribute("UVO");
			GoodsForm goodsForm = new GoodsForm();
			List<GoodsForm> commodityType = goodsService.getType();
	 
	    	model.addAttribute("goodsForm", goodsForm);
	    	model.addAttribute("commodityType", commodityType);
	    	CartForm cartForm = new CartForm();
	    	cartForm.setGuestId(uvo.getGuestId());
	    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
			if(device.isNormal()) {
	    		return "shop/editGuest";
	    	} else {
	    		return "mobile/editGuest";
	    	}
		}
		if (!guestForm.getPassword().equals(guestForm.getPasswordConfirm())) {
			log.info("密码验证出错");
			model.addAttribute("message1", "密码和确认密码必须一致！");
			UVO uvo = (UVO)session.getAttribute("UVO");
			GoodsForm goodsForm = new GoodsForm();
			List<GoodsForm> commodityType = goodsService.getType();
	 
	    	model.addAttribute("goodsForm", goodsForm);
	    	model.addAttribute("commodityType", commodityType);
	    	CartForm cartForm = new CartForm();
	    	cartForm.setGuestId(uvo.getGuestId());
	    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
			if(device.isNormal()) {
	    		return "shop/editGuest";
	    	} else {
	    		return "mobile/editGuest";
	    	}
		}
		log.info("修改客户信息");
		guestForm.setUpdateUser(guestForm.getGuestId());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		guestForm.setUpdateTime(dateformat.format(date));
		boolean result = guestService.editGuest(guestForm);
		if(!result) {
			throw new SQLException("客户信息更新失败！");
		}
		UVO uvo = new UVO();
		uvo.setGuestId(guestForm.getGuestId());
		uvo.setGuestName(guestForm.getGuestName());
		uvo.setPassword(guestForm.getPasswordConfirm());
		uvo.setGender(guestForm.getGender());
		uvo.setEmail(guestForm.getEmail());
		uvo.setMobile(guestForm.getMobile());
		uvo.setQq(guestForm.getQq());
		uvo.setPhone(guestForm.getPhone());
		uvo.setZip(guestForm.getZip());
		session.setAttribute("UVO", uvo);
		GoodsForm goodsForm = new GoodsForm();
//    	goodsForm.setType("粮食");
//    	model.addAttribute("goodsForm", goodsForm);
//    	model.addAttribute("list", goodsService.searchGoodsList(goodsForm));
		List<GoodsForm> commodityType = goodsService.getType();
 
    	model.addAttribute("goodsForm", goodsForm);
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setCount(i+"F");
			commodityType.get(i-1).setCss("columnT"+" "+"columnT-"+(i%6+1));
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	model.addAttribute("list", goodsService.searchGoodsList(goodsForm));
    	GoodsForm goodsFormForId = new GoodsForm();
    	for(int i=0;i<commodityType.size();i++){
    		goodsFormForId.setCommodityTypeId(commodityType.get(i).getCommodityTypeId());
    		commodityType.get(i).setList(goodsService.searchGoodsListLimit(goodsFormForId));
    	}	
    	model.addAttribute("commodityType", commodityType);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	model.addAttribute("list", goodsService.getTypeList(goodsForm));
    	
    	
		
	
    	
    	
    	
    	if(device.isNormal()) {
    		return "shop/index";
    	} else {
    		return "mobile/index";
    	}
	}
}
