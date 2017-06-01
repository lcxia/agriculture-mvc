package cn.agriculture.web.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.agriculture.web.form.ListBean;
import cn.agriculture.web.form.ReceiveForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CartService;
import cn.agriculture.web.service.GoodsService;
import cn.agriculture.web.service.GuestService;
import cn.agriculture.web.service.ReceiveService;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;

@Slf4j
@Controller("CartController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	ReceiveService receiveservice;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "initCart", method = RequestMethod.GET)
    public String initCart(Model model, HttpSession session, Device device) throws SQLException {
    	log.info("购物车初始化");
    	GoodsForm goodsForm = new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum1=commodityType.size();
		for(int i=1;i<=sum1;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
    		return "redirect:/initGuestLogin";
    	}
    	CartForm cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	List<CartForm> cartFormList=cartService.searchCartList(cartForm);
    	BigDecimal sum=new BigDecimal(0);
    	for(int i=0;i<cartFormList.size();i++){
    		BigDecimal smallSumPrice=new BigDecimal(Double.toString(Double.valueOf(cartFormList.get(i).getCount())*Double.valueOf(cartFormList.get(i).getRetailPrice())));	
    		cartFormList.get(i).setSmallSumPrice(String.valueOf(smallSumPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
    		sum.add(smallSumPrice);
    	}
    	cartForm.setSumPrice(String.valueOf(sum));
    	model.addAttribute("cartForm",cartForm);
    	model.addAttribute("cartList", cartFormList);
    	model.addAttribute("list", cartService.searchAlipayHistoryList(cartForm));
    	if(device.isNormal()) {
    		return "shop/cart/cart-1";
    	} else {
    		return "mobile/cart/cart-1";
    	}
    }
	
	/**
	 * 旧系统使用的加入购物车，新系统不再使用
	 * @param model
	 * @param session
	 * @param cartForm
	 * @param device
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "addCart", method = RequestMethod.POST)
    public String executeAddCart(Model model, HttpSession session, CartForm cartForm, Device device) throws SQLException {
    	log.info("追加购物车");
    	GoodsForm goodsForm = new GoodsForm();
		goodsForm.setCommodityId(cartForm.getCommodityId());
//		goodsForm.setType("粮食");
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
        			if(device.isNormal()) {
        	    		return "shop/goods/goodsDetail";
        	    	} else {
        	    		return "mobile/goods/goodsDetail";
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
    
    @RequestMapping(value = "delCart", method = RequestMethod.GET)
    public String executeDelCart(Model model, HttpSession session, CartForm cartForm, Device device) throws SQLException {
    	log.info("删除购物车");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
    		return "redirect:/initGuestLogin";
    	}
    	cartForm.setUpdateUser(uvo.getGuestName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cartForm.setUpdateTime(dateformat.format(date));
    	boolean result = cartService.delCart(cartForm);
    	if (!result) {
    		throw new SQLException("删除购物车失败！");
    	}
    	cartForm.setGuestId(uvo.getGuestId());
    	GoodsForm goodsForm=new GoodsForm();
//    	goodsForm.setType("粮食");
//    	model.addAttribute("goodsForm", goodsForm);
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	model.addAttribute("list", cartService.searchAlipayHistoryList(cartForm));
    	cartForm.setGuestId(uvo.getGuestId());
    	List<CartForm> cartFormList=cartService.searchCartList(cartForm);
    	for(int i=0;i<cartFormList.size();i++){
    		BigDecimal smallSumPrice=new BigDecimal(Double.toString(Double.valueOf(cartFormList.get(i).getCount())*Double.valueOf(cartFormList.get(i).getRetailPrice())));	
    		cartFormList.get(i).setSmallSumPrice(String.valueOf(smallSumPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
    		
    	}
    	model.addAttribute("cartForm",cartForm);
    	model.addAttribute("cartList", cartFormList);
    	if(device.isNormal()) {
    		return "shop/cart/cart-1";
    	} else {
    		return "mobile/cart/cart-1";
    	}
    }
    
    @RequestMapping(value = "alipayConfirm", method = RequestMethod.POST,params="delChoosedCart")
	public String executeDelChoosedCart(Model model, HttpSession session,
			CartForm cartForm, Device device) throws SQLException {
		log.info("删除选中购物车");
		UVO uvo = (UVO) session.getAttribute("UVO");
		if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
			return "redirect:/initGuestLogin";
		}
		List<ListBean> listBean = cartForm.getListBean();
		int b = listBean.size();
		for (int k = 0; k < b;k++) {				
			String check = listBean.get(k).getCheckArray();
			if (check != null) {
				cartForm.setCount(listBean.get(k).getCountArray());
				cartForm.setCommodityId(listBean.get(k).getCommodityId());
				cartForm.setCartId(listBean.get(k).getCartId());
				cartForm.setUpdateUser(uvo.getGuestName());
				Date date = new Date();
				SimpleDateFormat dateformat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				cartForm.setUpdateTime(dateformat.format(date));

				boolean result = cartService.delCart(cartForm);
				if (!result) {
					throw new SQLException("删除购物车失败！");
				}				
			}
		}
		cartForm.setGuestId(uvo.getGuestId());
		GoodsForm goodsForm = new GoodsForm();
		// goodsForm.setType("粮食");
		// model.addAttribute("goodsForm", goodsForm);
		List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
		model.addAttribute("cartList", cartService.searchCartList(cartForm));
		model.addAttribute("list",cartService.searchAlipayHistoryList(cartForm));
    	cartForm.setGuestId(uvo.getGuestId());
    	List<CartForm> cartFormList=cartService.searchCartList(cartForm);
    	for(int i=0;i<cartFormList.size();i++){
    		BigDecimal smallSumPrice=new BigDecimal(Double.toString(Double.valueOf(cartFormList.get(i).getCount())*Double.valueOf(cartFormList.get(i).getRetailPrice())));	
    		cartFormList.get(i).setSmallSumPrice(String.valueOf(smallSumPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
    	}
    	model.addAttribute("cartList", cartFormList);
		if (device.isNormal()) {
			return "shop/cart/cart-1";
		} else {
			return "mobile/cart/cart-1";
		}	
	}
	
	
	
	@RequestMapping(value = "alipayConfirm", method = RequestMethod.POST,params="Go")
	public String alipayConfirm(Model model, HttpSession session,
			CartForm cartForm, Device device) throws SQLException {
		GoodsForm goodsForm = new GoodsForm();
		// goodsForm.setType("粮食");
		// model.addAttribute("goodsForm", goodsForm);
		List<GoodsForm> commodityType = goodsService.getType();
    	int sum1=commodityType.size();
		for(int i=1;i<=sum1;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
		log.info("确认支付");
		// CartForm cartForm = new CartForm();
		UVO uvo = (UVO) session.getAttribute("UVO");
		if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
			return "redirect:/initGuestLogin";
		}

		List<ListBean> listBean = cartForm.getListBean();
		if(listBean==null)
		{
			cartForm = new CartForm();
	    	cartForm.setGuestId(uvo.getGuestId());
	    	List<CartForm> cartFormList=cartService.searchCartList(cartForm);	
	    	model.addAttribute("cartForm",cartForm);
	    	model.addAttribute("cartList", cartFormList);
	    	model.addAttribute("list", cartService.searchAlipayHistoryList(cartForm));
	    	model.addAttribute("message", "对不起，您的购物车为空，请选择商品结算");
			if(device.isNormal()) {
    		return "shop/cart/cart-1";
    	} else {
    		return "mobile/cart/cart-1";
    	}						
		}
		int b = listBean.size();
		String cartIds = "";
		for (int i = 0; i < b; i++) {
			cartForm.setUpdateUser(uvo.getGuestName());
			Date date = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			cartForm.setUpdateTime(dateformat.format(date));
			cartForm.setCommodityId(listBean.get(i).getCommodityId());
			cartForm.setCount(listBean.get(i).getCountArray());
			cartForm.setGuestId(uvo.getGuestId());
			String check = listBean.get(i).getCheckArray();
			if (check != null) {
				cartForm.setCartId(listBean.get(i).getCartId());
				boolean result = cartService.editStockByCart(cartForm);
				if (!result) {
					model.addAttribute("message", "库存不足");
					CartForm cartForm1 = new CartForm();
			    	cartForm1.setGuestId(uvo.getGuestId());
					List<CartForm> cartFormList=cartService.searchCartList(cartForm1);
			    	for(int n=0;n<cartFormList.size();n++){
			    		BigDecimal smallSumPrice=new BigDecimal(Double.toString(Double.valueOf(cartFormList.get(n).getCount())*Double.valueOf(cartFormList.get(n).getRetailPrice())));	
			    		cartFormList.get(n).setSmallSumPrice(String.valueOf(smallSumPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
			    	}
					model.addAttribute("cartForm",cartForm);
			    	model.addAttribute("cartList", cartFormList);
					if(device.isNormal()) {
			    		return "shop/cart/cart-1";
			    	} else {
			    		return "mobile/cart/cart-1";
			    	}						
				}
				boolean hisResult = cartService.updateCart(cartForm);
				if (!hisResult) {
					throw new SQLException("添加支付宝失败");
				}
				cartIds = cartIds + ",'" + listBean.get(i).getCartId() + "'";
			}

		}
		if("".equals(cartIds))
		{cartForm = new CartForm();
    	cartForm.setGuestId(uvo.getGuestId());
    	List<CartForm> cartFormList=cartService.searchCartList(cartForm);
    	BigDecimal sum=new BigDecimal(0);
    	for(int i=0;i<cartFormList.size();i++){
    		BigDecimal smallSumPrice=new BigDecimal(Double.toString(Double.valueOf(cartFormList.get(i).getCount())*Double.valueOf(cartFormList.get(i).getRetailPrice())));	
    		cartFormList.get(i).setSmallSumPrice(String.valueOf(smallSumPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
    		sum.add(smallSumPrice);
    	}
    	cartForm.setSumPrice(String.valueOf(sum));
    	model.addAttribute("cartForm",cartForm);
    	model.addAttribute("cartList", cartFormList);
    	model.addAttribute("list", cartService.searchAlipayHistoryList(cartForm));
			model.addAttribute("message", "对不起，您未选择购买任何商品，请选择后购买");
			if(device.isNormal()) {
	    		return "shop/cart/cart-1";
	    	} else {
	    		return "mobile/cart/cart-1";
	    	}
		}
		cartForm.setCartId(cartIds.substring(1));
		// cartForm.setGuestId(uvo.getGuestId());
		List<CartForm> list = cartService.searchCartListForCartId(cartForm);
		AlipayForm alipayForm = new AlipayForm();
		String body = "您购买的商品如下：";
		Double price = 0d;
		for (CartForm item : list) {
			body = body
					+ "品名："
					+ item.getCommodityName()
					+ ", 数量："
					+ item.getCount()
					+ ", 总价："
					+ String.valueOf(Double.valueOf(item.getCount())
							* Double.valueOf(item.getRetailPrice())) + ";";
			price = price + Double.valueOf(item.getCount())
					* Double.valueOf(item.getRetailPrice());
		}
		alipayForm.setBody(body);
		alipayForm.setOutTradeNo(list.get(0).getCartId());
		// 不满88元加8元邮费
		if (price < 88) {
			price = price + 8;
			body = body + "(由于本次订单未满88元，加收您邮费8元)";
		}
		alipayForm.setCartFormList(list);
		alipayForm.setPrice(price.toString());
    	GuestForm guestForm=new GuestForm();
    	guestForm.setGuestId(uvo.getGuestId());
   	 String addressDefault=guestService.searchAddressId(guestForm).getAddressId();
   	 model.addAttribute("addressDefault", addressDefault);
		ReceiveForm receiveForm=new ReceiveForm();
		receiveForm.setGuestId(uvo.getGuestId());
		List<ReceiveForm> list1=receiveservice.searchlist(receiveForm);
		model.addAttribute("list",list1);
		String host = env.getProperty("host.web");
		alipayForm.setShowUrl(host + "/initCart");
		alipayForm.setSubject(body);
		model.addAttribute("alipayForm", alipayForm);
		cartForm.setGuestId(uvo.getGuestId());		
		model.addAttribute("cartList", cartService.searchCartList(cartForm));
	
		if (device.isNormal()) {
			return "shop/cart/cart-2";
		} else {
			return "mobile/cart/cart-2";
		}
	}
    /*
    @RequestMapping(value = "replayAlipay", method = RequestMethod.GET)
    public String replayAlipay(Model model, AlipayForm alipayForm, HttpSession session, Device device) {
    	GoodsForm goodsForm=new GoodsForm();
//		goodsForm.setType("粮食");
//		model.addAttribute("goodsForm", goodsForm);
    	List<GoodsForm> commodityType = goodsService.getType();
 
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
    	log.info("重新支付");
    	AlipayForm result = cartService.searchAlipayHistory(alipayForm);
    	alipayForm.setBody(result.getBody());
    	alipayForm.setOutTradeNo(result.getOutTradeNo());
    	alipayForm.setPrice(result.getPrice());
    	alipayForm.setReceiveAddress(result.getReceiveAddress());
    	alipayForm.setReceiveMobile(result.getReceiveMobile());
    	alipayForm.setReceiveName(result.getReceiveName());
    	alipayForm.setReceivePhone(result.getReceivePhone());
    	alipayForm.setReceiveZip(result.getReceiveZip());
    	alipayForm.setShowUrl(result.getShowUrl());
    	alipayForm.setSubject(result.getSubject());
    	model.addAttribute("alipayForm", alipayForm);
    	CartForm cartForm = new CartForm();
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
    		return "redirect:/initGuestLogin";
    	}
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
        if(device.isNormal()) {
    		return "shop/alipay/replayAlipayConfirm";
    	} else {
    		return "mobile/alipay/replayAlipayConfirm";
    	}
    }
    */
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String order(Model model, AlipayForm alipayForm, HttpSession session, Device device) {
    	GoodsForm goodsForm=new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	log.info("我的订单");
    	CartForm cartForm = new CartForm();
    	model.addAttribute("cartForm", cartForm);
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
    		return "redirect:/initGuestLogin";
    	}
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	model.addAttribute("pagesList", cartService.searchOrderListCount(cartForm));
    	model.addAttribute("orderList", cartService.searchOrderList(cartForm, alipayForm.getIndex()));
    	model.addAttribute("alipayForm", alipayForm);
        if(device.isNormal()) {
    		return "shop/order";
    	} else {
    		return "mobile/order";
    	}
    }
    
    @RequestMapping(value = "replayOrder", method = RequestMethod.GET)
    public String replayAlipayOrder(Model model, AlipayForm alipayForm, HttpSession session, Device device) {
    	GoodsForm goodsForm=new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	log.info("重新支付");
    	AlipayForm result = cartService.searchAlipayHistory(alipayForm);
    	alipayForm.setOutTradeNo(result.getOutTradeNo());
    	alipayForm.setBody(result.getBody());
    	alipayForm.setPrice(result.getPrice());
    	alipayForm.setReceiveAddress(result.getReceiveAddress());
    	alipayForm.setReceiveMobile(result.getReceiveMobile());
    	alipayForm.setReceiveName(result.getReceiveName());
    	alipayForm.setReceivePhone(result.getReceivePhone());
    	alipayForm.setReceiveZip(result.getReceiveZip());
    	alipayForm.setShowUrl(result.getShowUrl());
    	alipayForm.setSubject(result.getSubject());
    	model.addAttribute("alipayForm", alipayForm);
    	CartForm cartForm = new CartForm();
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
    		return "redirect:/initGuestLogin";
    	}
    	cartForm.setGuestId(uvo.getGuestId());
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
//    	model.addAttribute("orderList", cartService.searchOrderList(cartForm));
    	if (device.isNormal()) {
	        model.addAttribute("sHtmlText", alipayRequestWeb(alipayForm));
		} else {
	        model.addAttribute("sHtmlText", alipayRequestMobile(alipayForm));
		}
		return "manager/charge/alipay";
    }
    
    private String alipayRequestWeb(AlipayForm alipayForm) {
		// 支付类型
	    String payment_type = "1";
	    // 必填，不能修改
	    // 服务器异步通知页面路径
	    String host = env.getProperty("host.web");
	    String notify_url = host + "/initReturn";
	    // 需http://格式的完整路径，不能加?id=123这类自定义参数

	    // 页面跳转同步通知页面路径
	    String return_url = host + "/initPayResult";

	    // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	    
	    // 商户订单号
	    String out_trade_no = alipayForm.getOutTradeNo();
	    // 订单名称
	    String subject = alipayForm.getSubject();
	    // 付款金额
        String total_fee = alipayForm.getPrice();

        // 订单描述
        String body = alipayForm.getBody();

        // 商品展示地址
        String show_url = alipayForm.getShowUrl();
        // 需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
        
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
        
        // 收货人姓名
        String receive_name = alipayForm.getReceiveName();
        // 收货人地址
        String receive_address = alipayForm.getReceiveAddress();
        // 收货人邮编
        String receive_zip = alipayForm.getReceiveZip();
        // 收货人电话号码
        String receive_phone = alipayForm.getReceivePhone();
        // 收货人手机号码
        String receive_mobile = alipayForm.getReceiveMobile();

        body = body + ";" + receive_name + ";" + receive_address + ";" + receive_zip + ";" + receive_phone + ";" + receive_mobile;
        
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("body", body);
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        return sHtmlText;
	}
    
    private String alipayRequestMobile(AlipayForm alipayForm) {
		// 支付类型
	    String payment_type = "1";
	    // 必填，不能修改
	    // 服务器异步通知页面路径
	    String host = env.getProperty("host.mobile");
	    String notify_url = host + "/initReturn";
	    // 需http://格式的完整路径，不能加?id=123这类自定义参数

	    // 页面跳转同步通知页面路径
	    String return_url = host + "/initPayResult";

	    // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	    
	    // 商户订单号
	    String out_trade_no = alipayForm.getOutTradeNo();
	    // 订单名称
	    String subject = alipayForm.getSubject();
	    // 付款金额
        String total_fee = alipayForm.getPrice();

        // 订单描述
        String body = alipayForm.getBody();

        // 商品展示地址
        String show_url = alipayForm.getShowUrl();
        // 需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
        
		//超时时间
		String it_b_pay = "";
		//选填

		//钱包token
		String extern_token = "";
		//选填
		
        // 收货人姓名
        String receive_name = alipayForm.getReceiveName();
        // 收货人地址
        String receive_address = alipayForm.getReceiveAddress();
        // 收货人邮编
        String receive_zip = alipayForm.getReceiveZip();
        // 收货人电话号码
        String receive_phone = alipayForm.getReceivePhone();
        // 收货人手机号码
        String receive_mobile = alipayForm.getReceiveMobile();

        body = body + ";" + receive_name + ";" + receive_address + ";" + receive_zip + ";" + receive_phone + ";" + receive_mobile;
        
        Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("body", body);
		sParaTemp.put("it_b_pay", it_b_pay);
		sParaTemp.put("extern_token", extern_token);

        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        return sHtmlText;
	}
    
    @RequestMapping(value = "deleteOrder", method = RequestMethod.GET)
    public String deleteOrder(Model model, AlipayForm alipayForm, HttpSession session, Device device) {
    	GoodsForm goodsForm=new GoodsForm();
    	List<GoodsForm> commodityType = goodsService.getType();
    	int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	log.info("删除我的订单");
    	CartForm cartForm = new CartForm();
    	model.addAttribute("cartForm", cartForm);
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	if (uvo == null || StringUtils.isEmpty(uvo.getGuestId())) {
    		return "redirect:/initGuestLogin";
    	}
    	cartForm.setGuestId(uvo.getGuestId());
    	cartService.deleteOrder(alipayForm);
    	model.addAttribute("cartList", cartService.searchCartList(cartForm));
    	model.addAttribute("pagesList", cartService.searchOrderListCount(cartForm));
    	model.addAttribute("orderList", cartService.searchOrderList(cartForm, 0));
        if(device.isNormal()) {
    		return "shop/order";
    	} else {
    		return "mobile/order";
    	}
    }
}
