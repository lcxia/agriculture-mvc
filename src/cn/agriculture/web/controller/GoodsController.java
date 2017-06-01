package cn.agriculture.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.CartForm;
import cn.agriculture.web.form.GoodsForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CartService;
import cn.agriculture.web.service.GoodsService;

@Slf4j
@Controller("GoodsController")
@RequestMapping("/")
public class GoodsController {
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	CartService cartService;
	
    @RequestMapping(value = "initGoods", method = RequestMethod.GET)
    public String initGoods(Model model, HttpSession session, GoodsForm goodsForm, Device device) throws UnsupportedEncodingException {
    	log.info("商品列表初始化");
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	model.addAttribute("orderTypeId", 1);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "initGoodsByPopularDesc", method = RequestMethod.GET)
    public String initGoodsByPopularDesc(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以人气为条件商品列表初始化");
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	model.addAttribute("list", goodsService.searchGoodsListByPopularDesc(goodsForm));
    	model.addAttribute("orderTypeId", 2);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "initGoodsByPopular", method = RequestMethod.GET)
    public String initGoodsByPopular(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以人气为条件商品列表初始化");
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	model.addAttribute("list", goodsService.searchGoodsListByPopular(goodsForm));
    	model.addAttribute("orderTypeId", 3);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "initGoodsByPriceDesc", method = RequestMethod.GET)
    public String initGoodsByPriceDesc(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以价格为条件商品列表初始化");
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	model.addAttribute("list", goodsService.searchGoodsListByPriceDesc(goodsForm));
    	model.addAttribute("orderTypeId", 4);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "initGoodsByPrice", method = RequestMethod.GET)
    public String initGoodsByPrice(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以价格为条件商品列表初始化");
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	model.addAttribute("list", goodsService.searchGoodsListByPrice(goodsForm));
    	model.addAttribute("orderTypeId", 5);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "initGoodsDetail", method = RequestMethod.GET)
    public String initGoodsDetail(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("商品明细初始化");
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("orderlist", goodsService.searchGoodsListOrder());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	model.addAttribute("goodsForm", goodsService.searchGoods(goodsForm));
    	if(device.isNormal()) {
    		return "shop/goods/details";
    	} else {
    		return "mobile/goods/details";
    	}
    }
    
    @RequestMapping(value = "selectGoods", method = RequestMethod.POST)
    public String  selectGoods(Model model,HttpSession session,   GoodsForm goodsForm, Device device){
    	log.info("检索相关商品");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	List<GoodsForm> list=goodsService.searchGoodsListrelative(goodsForm);
    	model.addAttribute("list",list);
    	model.addAttribute("orderTypeId", 1);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	model.addAttribute("goodsForm", goodsForm);
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    
    
    @RequestMapping(value = "selectGoods1", method = RequestMethod.GET)
    public String  selectGoods1(Model model,HttpSession session,GoodsForm goodsForm,Device device){
    	log.info("检索相关商品");
    		try {
    			String commodityName=new String(goodsForm.getCommodityName().getBytes("iso8859-1"),"utf-8");
    			goodsForm.setCommodityName(commodityName);
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	List<GoodsForm> list=goodsService.searchGoodsListrelative(goodsForm);
    	model.addAttribute("list",list);
    	model.addAttribute("orderTypeId", 1);
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	model.addAttribute("goodsForm", goodsForm);
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "selectGoodsByPopularDesc", method = RequestMethod.GET)
    public String selectGoodsByPopularDesc(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以人气为条件相关商品列表降序初始化");
    		try {
    			String commodityName=new String(goodsForm.getCommodityName().getBytes("iso8859-1"),"utf-8");
    			goodsForm.setCommodityName(commodityName);
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	goodsForm.setCommodityTypeId(null);
    	model.addAttribute("list", goodsService.searchGoodsListByPopularDesc(goodsForm));
    	model.addAttribute("orderTypeId", 2);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "selectGoodsByPopular", method = RequestMethod.GET)
    public String selectGoodsByPopular(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以人气为条件相关商品列表升序初始化");
    		try {
    			String commodityName=new String(goodsForm.getCommodityName().getBytes("iso8859-1"),"utf-8");
    			goodsForm.setCommodityName(commodityName);
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	goodsForm.setCommodityTypeId(null);
    	model.addAttribute("list", goodsService.searchGoodsListByPopular(goodsForm));
    	model.addAttribute("orderTypeId", 3);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "selectGoodsByPriceDesc", method = RequestMethod.GET)
    public String selectGoodsByPriceDesc(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以价格为条件商品列表降序初始化");
    		try {
    			String commodityName=new String(goodsForm.getCommodityName().getBytes("iso8859-1"),"utf-8");
    			goodsForm.setCommodityName(commodityName);
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	goodsForm.setCommodityTypeId(null);
    	model.addAttribute("list", goodsService.searchGoodsListByPriceDesc(goodsForm));
    	model.addAttribute("orderTypeId", 4);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
    @RequestMapping(value = "selectGoodsByPrice", method = RequestMethod.GET)
    public String selectGoodsByPrice(Model model, HttpSession session, GoodsForm goodsForm, Device device) {
    	log.info("以价格为条件商品列表升序初始化");
    		try {
    			String commodityName=new String(goodsForm.getCommodityName().getBytes("iso8859-1"),"utf-8");
    			goodsForm.setCommodityName(commodityName);
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	if(goodsForm.getCommodityTypeId()==null)
    	{
			model.addAttribute("list", goodsService.getTypeList(goodsForm));
	    	model.addAttribute("goodsForm", goodsForm);
    	}
    	else
    		{model.addAttribute("goodsForm", goodsForm);
    		model.addAttribute("list", goodsService.getTypeList(goodsForm));
    		}
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null) {
    		uvo = new UVO();
    		session.setAttribute("UVO", uvo);
    	}
    	goodsForm.setCommodityTypeId(null);
    	model.addAttribute("list", goodsService.searchGoodsListByPrice(goodsForm));
    	model.addAttribute("orderTypeId", 5);
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	if(device.isNormal()) {
    		return "shop/list";
    	} else {
    		return "mobile/list";
    	}
    }
}
