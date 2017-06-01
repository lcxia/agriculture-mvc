package cn.agriculture.web.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.AlipayForm;
import cn.agriculture.web.form.CartForm;
import cn.agriculture.web.form.GoodsForm;
import cn.agriculture.web.form.GuestForm;
import cn.agriculture.web.form.ReceiveForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.AlipayService;
import cn.agriculture.web.service.CartService;
import cn.agriculture.web.service.GoodsService;
import cn.agriculture.web.service.GuestService;
import cn.agriculture.web.service.ReceiveService;
@Slf4j
@Controller("DetailsController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class DetailsController {
	@Autowired
	CartService cartService;
	
	@Autowired
	ReceiveService receiveservice;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	AlipayService alipayService;
	
	@Autowired
	GuestService guestService;

	@Autowired
	private Environment env;
	
	
	@RequestMapping(value = "submit", method = RequestMethod.POST,params="AlipaySubmit")
	public String executeAlipaySubmit(Model model, HttpSession session, GoodsForm goodsForm, CartForm cartForm, Device device) throws SQLException {
		UVO uvo = (UVO)session.getAttribute("UVO");
		//匿名购买
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId()) || uvo.getGuestId().length() > 4) {
    		if (uvo == null || StringUtils.isEmpty(uvo.getGuestId()) || "Guest".equals(uvo.getGuestId().substring(0, 5))) {
    			List<GoodsForm> commodityType = goodsService.getType();
    	    	int sum=commodityType.size();
    			for(int i=1;i<=sum;i++){
    				commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
    			}
    	    	model.addAttribute("goodsForm", goodsForm);
    	    	model.addAttribute("commodityType",commodityType);
    			uvo = new UVO();
    			Date date = new Date();
        		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        		uvo.setUserId("Guest" + dateformat.format(date));
        		uvo.setGuestId("Guest" + dateformat.format(date));
        		uvo.setUserName("来宾" + dateformat.format(date));
        		log.info("匿名购买商品销售页面初始化。");
        		AlipayForm alipayForm = new AlipayForm();
        		cartForm.setGuestId(uvo.getGuestId());
        		alipayForm = cartService.searchAlipay(cartForm);
        		List<CartForm> cartList = new ArrayList<>();
        		model.addAttribute("cartList", cartList);
        		if (alipayForm == null) {
        			model.addAttribute("message", "库存不够！");
        	    	model.addAttribute("orderlist", goodsService.searchGoodsListOrder());
        	    	model.addAttribute("goodsForm", goodsService.searchGoods(goodsForm));
        			if(device.isNormal()) {
        	    		return "shop/goods/details";
        	    	} else {
        	    		return "mobile/goods/details";
        	    	}
        		}
        		model.addAttribute("alipayForm", alipayForm);
        		if(device.isNormal()) {
        			return "shop/alipay/guestAlipayConfirm";
        		} else {
        			return "mobile/alipay/guestAlipayConfirm";
        		}
    		}
    	}
		//非匿名购买
		GoodsForm goodsForm1=new GoodsForm();
		List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("commodityType",commodityType);
    	goodsForm1.setCommodityId(goodsForm.getCommodityId());
    	model.addAttribute("goodsForm", goodsForm1);
    	cartForm.setGuestId(uvo.getGuestId());
    	//设置updateTime
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cartForm.setUpdateTime(dateformat.format(date));
		//设置updateUser
		cartForm.setUpdateUser(uvo.getGuestName());
    	ReceiveForm receiveForm=new ReceiveForm();
    	receiveForm.setGuestId(uvo.getGuestId());
    	GuestForm guestForm=new GuestForm();
    	guestForm.setGuestId(uvo.getGuestId());
   	 String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
   	 model.addAttribute("addressDefault", addressDefault);
    	List<ReceiveForm> list=receiveservice.searchlist(receiveForm);
      	model.addAttribute("list", list);
      	AlipayForm alipayForm=new AlipayForm();
      	alipayForm=cartService.searchAlipayImmediately(cartForm);
      	model.addAttribute("alipayForm",alipayForm);
		if (alipayForm == null) {
	    	model.addAttribute("commodityType", commodityType);
			model.addAttribute("goodsForm", goodsService.searchGoods(goodsForm1));
			model.addAttribute("cartList", cartService.searchCartList(cartForm));
			model.addAttribute("message", "库存不够！");
			model.addAttribute("orderlist", goodsService.searchGoodsListOrder());
			if(device.isNormal()) {
	    		return "shop/goods/details";
	    	} else {
	    		return "mobile/goods/details";
	    	}
		}
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));		
    	if(device.isNormal()) {
			return "shop/cart/cart-2";
		} else {
			return "mobile/cart/cart-2";
		}
	}
    @RequestMapping(value = "submit", method = RequestMethod.POST,params="addCart")
    public String executeAddCart(Model model, HttpSession session, CartForm cartForm, Device device) throws SQLException {
    	log.info("追加购物车");
    	GoodsForm goodsForm = new GoodsForm();
		goodsForm.setCommodityId(cartForm.getCommodityId());
		List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("commodityType",commodityType);
		model.addAttribute("goodsForm", goodsService.searchGoods(goodsForm));
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId()) || uvo.getGuestId().length() > 4) {
    		if (uvo == null || StringUtils.isEmpty(uvo.getGuestId()) || "Guest".equals(uvo.getGuestId().substring(0, 5))) {
    			uvo = new UVO();
    			Date date = new Date();
        		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        		uvo.setUserId("Guest" + dateformat.format(date));
        		uvo.setGuestId("Guest" + dateformat.format(date));
        		uvo.setUserName("来宾" + dateformat.format(date));
        		log.info("匿名购买商品销售页面初始化。");
        		AlipayForm alipayForm = new AlipayForm();
        		cartForm.setGuestId(uvo.getGuestId());
        		alipayForm = cartService.searchAlipay(cartForm);
        		List<CartForm> cartList = new ArrayList<>();
        		model.addAttribute("cartList", cartList);
        		if (alipayForm == null) {
        			model.addAttribute("message", "库存不够！");
        	    	model.addAttribute("orderlist", goodsService.searchGoodsListOrder());
        			if(device.isNormal()) {
        	    		return "shop/goods/details";
        	    	} else {
        	    		return "mobile/goods/details";
        	    	}
        		}
        		model.addAttribute("alipayForm", alipayForm);
        		if(device.isNormal()) {
        			return "shop/alipay/guestAlipayConfirm";
        		} else {
        			return "mobile/alipay/guestAlipayConfirm";
        		}
    		}
    	}
    	
    	cartForm.setUpdateUser(uvo.getGuestName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cartForm.setUpdateTime(dateformat.format(date));
		cartForm.setGuestId(uvo.getGuestId());
		AlipayForm alipayForm = new AlipayForm();
		alipayForm = cartService.searchAlipay(cartForm);
		if (alipayForm == null) {
			model.addAttribute("cartList", cartService.searchCartList(cartForm));
			model.addAttribute("message", "库存不够！");
			model.addAttribute("orderlist", goodsService.searchGoodsListOrder());
			if(device.isNormal()) {
	    		return "shop/goods/details";
	    	} else {
	    		return "mobile/goods/details";
	    	}
		}
    
    	boolean result = cartService.addCart(cartForm);
    	if (!result) {
    		throw new SQLException("追加购物车失败！");
    	}
    	List<CartForm> cartFormList=cartService.searchCartList(cartForm);
    	for(int i=0;i<cartFormList.size();i++){
    		BigDecimal smallSumPrice=new BigDecimal(Double.toString(Double.valueOf(cartFormList.get(i).getCount())*Double.valueOf(cartFormList.get(i).getRetailPrice())));	
    		cartFormList.get(i).setSmallSumPrice(String.valueOf(smallSumPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
    	}
    	model.addAttribute("cartList", cartFormList);
    	model.addAttribute("list", cartService.searchAlipayHistoryList(cartForm));
    	if(device.isNormal()) {
    		return "shop/cart/cart-1";
    	} else {
    		return "mobile/cart/cart-1";
    	}
    }
}
