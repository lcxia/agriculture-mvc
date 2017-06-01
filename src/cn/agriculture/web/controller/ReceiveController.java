package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.common.component.ItemListComponent;
import cn.agriculture.web.form.CartForm;
import cn.agriculture.web.form.GoodsForm;
import cn.agriculture.web.form.GuestForm;
import cn.agriculture.web.form.ReceiveForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CartService;
import cn.agriculture.web.service.GoodsService;
import cn.agriculture.web.service.GuestService;
import cn.agriculture.web.service.ReceiveService;

@Slf4j
@Controller("ReceiveController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class ReceiveController {
	
@Autowired
ReceiveService receiveservice;
@Autowired
GoodsService goodsService;
@Autowired
CartService cartservice;
@Autowired
ItemListComponent itemListComponent;

@Autowired
CartService cartService;
@Autowired
GuestService guestService;

@RequestMapping(value = "initReceive", method = RequestMethod.GET)
public String executeinitReceive(Model model,HttpSession session, @Valid @ModelAttribute("receiveForm") ReceiveForm receiveForm, Device device) throws SQLException {
	log.info("初始化收货人信息");
	 GuestForm guestForm = new GuestForm();
	 UVO uvo=(UVO) session.getAttribute("UVO");
	 guestForm.setGuestId(uvo.getGuestId());
	 String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
	 model.addAttribute("addressDefault", addressDefault);
	GoodsForm goodsForm = new GoodsForm();
//	goodsForm.setType("粮食");
//	model.addAttribute("commodityType", goodsService.getType(goodsForm));
//	model.addAttribute("goodsForm", goodsForm);
	List<GoodsForm> commodityType = goodsService.getType();
	int sum=commodityType.size();
	for(int i=1;i<=sum;i++){
		commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
	}
	model.addAttribute("goodsForm", goodsForm);
	model.addAttribute("commodityType",commodityType);
	receiveForm.setGuestId(uvo.getGuestId());
//	session.setAttribute("UVO", uvo);
	 model.addAttribute("receiveForm", receiveForm);
	CartForm cartForm = new CartForm();
 	cartForm.setGuestId(uvo.getGuestId());
 	model.addAttribute("cartList", cartService.searchCartList(cartForm));
  	List<ReceiveForm> list=receiveservice.searchlist(receiveForm);
  	model.addAttribute("list", list);
	if(device.isNormal())
	{	return "shop/address";
	}
	else {
		return "mobile/address";
	}	
}
@RequestMapping(value = "addReceive", method = RequestMethod.POST)
public String executeaddReceive(Model model,HttpSession session, @Valid @ModelAttribute("receiveForm") ReceiveForm receiveForm, BindingResult results,Device device)throws SQLException {
	 GuestForm guestForm = new GuestForm();
	 UVO uvo=(UVO) session.getAttribute("UVO");
	 guestForm.setGuestId(uvo.getGuestId());
	 String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
	 GoodsForm goodsForm = new GoodsForm();
	 List<GoodsForm> commodityType = goodsService.getType();
		int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
		model.addAttribute("goodsForm", goodsForm);
		model.addAttribute("commodityType",commodityType);
	if(receiveForm.getAddressId().equals(""))
    {log.info("添加收货人信息");
    CartForm cartForm = new CartForm();
	cartForm.setGuestId(uvo.getGuestId());
	model.addAttribute("cartList", cartService.searchCartList(cartForm));
	if(results.hasErrors()){
	   List<ReceiveForm> list=receiveservice.searchlist(receiveForm);
	   model.addAttribute("list", list);
	   model.addAttribute("receiveForm", receiveForm);
		 model.addAttribute("addressDefault", addressDefault);
	   if(device.isNormal())
	   {return "shop/address";}
	   else 
	   {return "mobile/address";
	   }
   }
   boolean result=receiveservice.insertReceive(receiveForm);
   if(!result)
   {
	   throw new SQLException("收货地址追加失败！");
   }
   if("on".equals(receiveForm.getCheck()))
   {
	   addressDefault=receiveForm.getAddressId();
	   model.addAttribute("addressDefault", addressDefault);
   }
   else{
	   model.addAttribute("addressDefault", addressDefault);
   }
   ReceiveForm receiveForm1=new ReceiveForm();
   receiveForm1.setAddressId("");
   receiveForm1.setGuestId(uvo.getGuestId());
   model.addAttribute("receiveForm", receiveForm1);
   List<ReceiveForm> list=receiveservice.searchlist(receiveForm);
   model.addAttribute("list", list);
   if(device.isNormal())
	{	return "shop/address";
	}
	else {
		return "mobile/address";
	}	
   }
   else{   
   return executeEditReceive(model,session, receiveForm,results,device); 
   }
   }
@RequestMapping(value = "initEditReceive", method = RequestMethod.GET)
public String initEditReceive(Model model, HttpSession session, @Valid @ModelAttribute("receiveForm") ReceiveForm receiveForm, Device device) {
	log.info("修改收货人信息初始化");
	GuestForm guestForm = new GuestForm();
	UVO uvo=(UVO) session.getAttribute("UVO");
	guestForm.setGuestId(uvo.getGuestId());
	String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
	model.addAttribute("addressDefault", addressDefault);
	GoodsForm goodsForm = new GoodsForm();
	List<GoodsForm> commodityType = goodsService.getType();
	int sum=commodityType.size();
	for(int i=1;i<=sum;i++){
		commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
	}
	model.addAttribute("goodsForm", goodsForm);
	model.addAttribute("commodityType",commodityType);
	CartForm cartForm = new CartForm();
	cartForm.setGuestId(uvo.getGuestId());
	model.addAttribute("cartList", cartService.searchCartList(cartForm));
	receiveForm=receiveservice.searchReceive(receiveForm);
  	model.addAttribute("receiveForm", receiveForm);
  	List<ReceiveForm> list=receiveservice.searchlist(receiveForm);
  	model.addAttribute("list", list);
	if(device.isNormal()) {
		return "shop/address";
	} else {
		return "mobile/address";
	}
}
@RequestMapping(value = "editReceive", method = RequestMethod.POST)
public String executeEditReceive(Model model, HttpSession session, @Valid @ModelAttribute("receiveForm") ReceiveForm receiveForm, BindingResult results, Device device) throws SQLException {
	log.info("修改收货人信息");
	GuestForm guestForm = new GuestForm();
	UVO uvo=(UVO) session.getAttribute("UVO");
	guestForm.setGuestId(uvo.getGuestId());
	String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
	CartForm cartForm = new CartForm();
	cartForm.setGuestId(uvo.getGuestId());
	model.addAttribute("cartList", cartService.searchCartList(cartForm));
  	GoodsForm goodsForm = new GoodsForm();
	List<GoodsForm> commodityType = goodsService.getType();
	int sum=commodityType.size();
	for(int i=1;i<=sum;i++){
		commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
	}
	model.addAttribute("goodsForm", goodsForm);
	model.addAttribute("commodityType",commodityType);
	receiveForm.setGuestId(uvo.getGuestId());
	if (results.hasErrors()) {
		log.info("内容验证出错");
		
	}
    if(receiveservice.updateAddress(receiveForm)==1)
    { if("on".equals(receiveForm.getCheck()))
    	{addressDefault=receiveForm.getAddressId();
    	model.addAttribute("addressDefault", addressDefault);
    	}
       List<ReceiveForm>   list= receiveservice.searchlist(receiveForm);
    	model.addAttribute("list",list);
    	receiveForm=new ReceiveForm();
    	receiveForm.setGuestId(uvo.getGuestId());
    	receiveForm.setAddressId("");
    	model.addAttribute("receiveForm", receiveForm);
    	 model.addAttribute("addressDefault", addressDefault);
    }
    else 
    {
    	throw new SQLException("修改收货人信息失败！");
    }
	if(device.isNormal()) {
		return "shop/address";
	} else {
		return "mobile/address";
	}
}
@RequestMapping(value = "deleteReceive", method = RequestMethod.GET)
public String executeDeleteReceive(Model model, HttpSession session, @Valid @ModelAttribute("receiveForm") ReceiveForm receiveForm, BindingResult results, Device device) throws SQLException {
	    GuestForm guestForm = new GuestForm();
	    UVO uvo=(UVO) session.getAttribute("UVO");
		guestForm.setGuestId(uvo.getGuestId());
		String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
		model.addAttribute("addressDefault", addressDefault);
	    receiveForm.setGuestId(uvo.getGuestId());
	    GoodsForm goodsForm = new GoodsForm();
		List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
	    CartForm cartForm = new CartForm();
	    cartForm.setGuestId(uvo.getGuestId());
	    model.addAttribute("cartList", cartService.searchCartList(cartForm));
//	GuestForm guestForm=new GuestForm();
//	guestForm.setGuestId(receiveForm.getGuestId());
//    String addressDefault=guestService.searchAddress(guestForm).getAddress();
    if(receiveForm.getAddressId().equals(addressDefault)){
    	guestForm.setAddressId("");
    	boolean result=guestService.editGuestAddressId(guestForm);
    	if(result)
        {receiveservice.deleteAddress(receiveForm);
    	}
    	List<ReceiveForm> list= receiveservice.searchlist(receiveForm);
    	model.addAttribute("list",list);
    	ReceiveForm receiveForm1=new ReceiveForm();
    	receiveForm1.setGuestId(uvo.getGuestId());
    	receiveForm1.setAddressId("");
    	model.addAttribute("receiveForm", receiveForm1);
    	if(device.isNormal()) {
    		return "shop/address";
    	} else {
    		return "mobile/address";
    	}
    }
    else{
    	
    	int result= receiveservice.deleteAddress(receiveForm);
    	if(result==1)
     	{List<ReceiveForm> list= receiveservice.searchlist(receiveForm);
     	model.addAttribute("list",list);
     	ReceiveForm receiveForm1=new ReceiveForm();
     	receiveForm1.setGuestId(uvo.getGuestId());
     	receiveForm1.setAddressId("");
     	model.addAttribute("receiveForm", receiveForm1);
     	}
    	if(device.isNormal()) {
    		return "shop/address";
    	} else {
    		return "mobile/address";
    	}
	}
}
@RequestMapping(value = "defaultAddress", method = RequestMethod.GET)
public String setDefault(Model model, HttpSession session, @Valid @ModelAttribute("receiveForm") ReceiveForm receiveForm, BindingResult results, Device device) throws SQLException {
	UVO uvo = (UVO)session.getAttribute("UVO");
	receiveForm.setGuestId(uvo.getGuestId());
	GoodsForm goodsForm = new GoodsForm();
	List<GoodsForm> commodityType = goodsService.getType();
	int sum=commodityType.size();
	for(int i=1;i<=sum;i++){
		commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
	}
	model.addAttribute("goodsForm", goodsForm);
	model.addAttribute("commodityType",commodityType);
	CartForm cartForm = new CartForm();
	cartForm.setGuestId(uvo.getGuestId());
	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    model.addAttribute("receiveForm", receiveForm);
    GuestForm guestForm = new GuestForm();
	guestForm.setGuestId(uvo.getGuestId());
	String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
	    	guestForm.setAddressId(receiveForm.getAddressId());
	    	boolean result=guestService.editGuestAddressId(guestForm);
	    	if(result){ 
	    		addressDefault=guestForm.getAddressId();
	    		model.addAttribute("addressDefault",addressDefault);
	    		receiveForm.setAddressId("");
	       }
	    	else {
	    		throw new SQLException("默认地址设置失败！");
	    	}
	    	List<ReceiveForm> list= receiveservice.searchlist(receiveForm);
	    	model.addAttribute("list",list);
	    	if(device.isNormal()) {
	    		return "shop/address";
	    	} else {
	    		return "mobile/address";
	    	}
		}
}
