package cn.agriculture.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.GoodsForm;
import cn.agriculture.web.form.SpecialForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.GoodsService;
import cn.agriculture.web.service.SpecialService;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;

@Slf4j
@Controller("SpecialController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class SpecialController {
	@Autowired
	SpecialService specialService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "initSpecial", method = RequestMethod.GET)
	public String initSpecial(Model model) {
		log.info("活动参与者IP列表初始化");
		model.addAttribute("list", specialService.searchSpecial());
		return "manager/special/specialList";
	}
	
	@RequestMapping(value = "delSpecial", method = RequestMethod.GET)
	public String executeDelSpecial(Model model) {
		log.info("删除活动参与者IP列表");
		specialService.delSpecial();
		model.addAttribute("list", specialService.searchSpecial());
		return "manager/special/specialList";
	}
	
	@RequestMapping(value = "initSpecialAlipayComfirm", method = RequestMethod.GET)
	public String executeInitDistributorAlipayComfirm(Model model,HttpSession session, SpecialForm specialForm, HttpServletRequest request) {
		GoodsForm goodsForm=new GoodsForm();
//		goodsForm.setType("粮食");
//		model.addAttribute("goodsForm", goodsForm);
		List<GoodsForm> commodityType = goodsService.getType();
		int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
		UVO uvo=new UVO();
		session.setAttribute("UVO",uvo );
		log.info("专为活动准备的商品销售页面初始化");
		String remoteIp = "";
		remoteIp = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getHeader("X-Real-IP");
		}
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getRemoteAddr();
		}
		if (StringUtils.isEmpty(remoteIp) ||  "unknown".equalsIgnoreCase(remoteIp)) {
			remoteIp= request.getRemoteHost();
		}
		if (specialService.checkIp(specialForm, remoteIp)) {
			specialService.cutPrice(specialForm);
		}
		specialForm = specialService.searchAlipay(specialForm);
		model.addAttribute("specialForm", specialService.searchAlipay(specialForm));
		if(Double.valueOf(specialForm.getStock()) < 1) {
			model.addAttribute("message", "该商品为促销打折商品，目前已经卖完了，请关注我们下次活动，谢谢您的参与！");
		}
		return "mobile/special/specialAlipayConfirm";
	}
	
	@RequestMapping(value = "specialAlipaySubmit", method = RequestMethod.POST)
	public String executeSpecialAlipaySubmit(Model model, @Valid @ModelAttribute("specialForm") SpecialForm specialForm, BindingResult results, Device device) throws SQLException {
		GoodsForm goodsForm=new GoodsForm();
//		goodsForm.setType("粮食");
//		model.addAttribute("goodsForm", goodsForm);
		List<GoodsForm> commodityType = goodsService.getType();
		//model.addAttribute("specialForm", specialService.searchAlipay(specialForm));
		model.addAttribute("specialForm", specialForm);
		int sum=commodityType.size();
		for(int i=1;i<=sum;i++){
			commodityType.get(i-1).setPh("ico-nav"+" "+"ico-nav-"+(i%7+1));
		}
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType",commodityType);
    	model.addAttribute("goodsForm", goodsForm);
    	model.addAttribute("commodityType", commodityType);
		log.info("由分销商直接推荐的商品向支付宝发起支付请求。");
		if (results.hasErrors()) {
			log.info("内容验证出错");
			model.addAttribute("message", "该画面所有项目都是必填项，请认真填写！");
			if(device.isNormal()) {
				return "mobile/special/specialAlipayConfirm";
	    	} else {
	    		return "mobile/special/specialAlipayConfirm";
	    	}
		}
		specialForm.setUpdateUser("管理员");
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		specialForm.setUpdateTime(dateformat.format(date));
		boolean hisResult = specialService.addAlipayHistory(specialForm);
		if(!hisResult) {
			throw new SQLException("添加支付宝账单失败！");
		}
		boolean result = specialService.editStock(specialForm);
		if(!result) {
			model.addAttribute("message", "该商品为促销打折商品，目前已经卖完了，请关注我们下次活动，谢谢您的参与！");
			model.addAttribute("specialForm", specialService.searchAlipay(specialForm));
			return "mobile/special/specialAlipayConfirm";
		}
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
	    String out_trade_no = specialForm.getOutTradeNo();
	    // 订单名称
	    String subject = specialForm.getSubject();
	    // 付款金额
        String total_fee = specialForm.getPrice();

        // 订单描述
        String body = specialForm.getBody();

        // 商品展示地址
        String show_url = specialForm.getShowUrl();
        // 需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
        
		//超时时间
		String it_b_pay = "";
		//选填

		//钱包token
		String extern_token = "";
		//选填
		
        // 收货人姓名
        String receive_name = specialForm.getReceiveName();
        // 收货人地址
        String receive_address = specialForm.getReceiveAddress();
        // 收货人邮编
        String receive_zip = specialForm.getReceiveZip();
        // 收货人电话号码
        String receive_phone = specialForm.getReceivePhone();
        // 收货人手机号码
        String receive_mobile = specialForm.getReceiveMobile();

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
        model.addAttribute("sHtmlText", sHtmlText);
        return "manager/charge/alipay";
	}
}
