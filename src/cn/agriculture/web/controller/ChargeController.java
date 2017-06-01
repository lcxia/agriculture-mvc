package cn.agriculture.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.ChargeForm;
import cn.agriculture.web.form.ReturnForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.ChargeService;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;

@Slf4j
@Controller("ChargeController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class ChargeController {

	@Autowired
	ChargeService chargeService;
	
	@Autowired
	private Environment env;
	
    @RequestMapping(value = "initCharge", method = RequestMethod.GET)
    public String initCharge(Model model, HttpSession session) {
    	log.info("充值列表初始化");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	ChargeForm chargeForm = new ChargeForm();
    	chargeForm.setDistributorId(uvo.getUserId());
    	model.addAttribute("list", chargeService.searchChargeList(chargeForm));
        return "manager/charge/chargeList";
    }
    
    @RequestMapping(value = "initAddCharge", method = RequestMethod.GET)
    public String initAddDistributor(Model model) {
    	log.info("追加充值初始化");
    	ChargeForm chargeForm = new ChargeForm();
    	model.addAttribute("chargeForm", chargeForm);
    	return "manager/charge/addCharge";
    }
    
	@RequestMapping(value = "addCharge", method = RequestMethod.POST)
	public String executeAddCharge(Model model, HttpSession session, @Valid @ModelAttribute("chargeForm") ChargeForm chargeForm, BindingResult results) throws SQLException {
		if (results.hasErrors()) {
			log.info("内容验证出错");
			return "manager/charge/addCharge";}
		log.info("添加充值信息");
		UVO uvo = (UVO)session.getAttribute("UVO");
		chargeForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		chargeForm.setUpdateTime(dateformat.format(date));
		chargeForm.setDistributorId(uvo.getUserId());
		chargeForm.setChargeDate(dateformat.format(date));
		boolean result = chargeService.addCharge(chargeForm);
		if(!result) {
			throw new SQLException("充值信息添加失败！");
		}
		//model.addAttribute("list", chargeService.searchChargeList(chargeForm));
        //return "manager/charge/chargeList";
		
	    // 支付类型
	    String payment_type = "1";
	    // 必填，不能修改
	    // 服务器异步通知页面路径
	    String host = env.getProperty("host.web");
	    String notify_url = host + "/initChargeReturn";
	    // 需http://格式的完整路径，不能加?id=123这类自定义参数

	    // 页面跳转同步通知页面路径
	    String return_url = host + "/initCharge";

	    // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	    
	    // 商户订单号
	    String out_trade_no = chargeForm.getChargeId();
	    // 订单名称
	    String subject = "网络充值单";
	    // 付款金额
        String total_fee = chargeForm.getAmount();

        // 订单描述
        String body = "本店购买的充值单";

        // 商品展示地址
        String show_url = "www.52hny.cn";
        // 需以http://开头的完整路径，如：http://www.商户网站.com/myorder.html
        
		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
        
        // 收货人姓名
        String receive_name = chargeForm.getDistributorId();
        // 收货人地址
        String receive_address = uvo.getAddress();
        // 收货人邮编
        String receive_zip = "300000";
        // 收货人电话号码
        String receive_phone = uvo.getTelephone();
        // 收货人手机号码
        String receive_mobile = uvo.getTelephone();

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
        model.addAttribute("sHtmlText", sHtmlText);
        return "manager/charge/alipay";
	}
	
	@RequestMapping(value = "initChargeReturn", method = RequestMethod.POST)
	public void executeInitChargeReturn(Model model, ReturnForm returnForm, HttpServletResponse response) throws SQLException, IOException {
		log.info("这是一个支付宝主动调用会员充值信息的日志");
		log.info(returnForm.getOut_trade_no());
		log.info(returnForm.getTrade_no());
		log.info(returnForm.getTrade_status());
		log.info(returnForm.getNotify_id());
		log.info(returnForm.getSign());
		log.info("sign_type:" + returnForm.getSign_type());
		Map<String,String> params = new HashMap<String,String>();
		params.put("out_trade_no", returnForm.getOut_trade_no());
		params.put("trade_no", returnForm.getTrade_no());
		params.put("trade_status", returnForm.getTrade_status());
		params.put("notify_id", returnForm.getNotify_id());
		params.put("sign", returnForm.getSign());
		params.put("sign_type", returnForm.getSign_type());
		params.put("discount", returnForm.getDiscount());
		params.put("payment_type", returnForm.getPayment_type());
		params.put("subject", returnForm.getSubject());
		params.put("buyer_email", returnForm.getBuyer_email());
		params.put("gmt_create", returnForm.getGmt_create());
		params.put("notify_type", returnForm.getNotify_type());
		params.put("quantity", returnForm.getQuantity());
		params.put("seller_id", returnForm.getSeller_id());
		params.put("notify_time", returnForm.getNotify_time());
		params.put("body", returnForm.getBody());
		params.put("is_total_fee_adjust", returnForm.getIs_total_fee_adjust());
		params.put("total_fee", returnForm.getTotal_fee());
		params.put("gmt_payment", returnForm.getGmt_payment());
		params.put("seller_email", returnForm.getSeller_email());
		params.put("price", returnForm.getPrice());
		params.put("buyer_id", returnForm.getBuyer_id());
		params.put("use_coupon", returnForm.getUse_coupon());
		PrintWriter out=response.getWriter();
		if(AlipayNotify.verify(params)){
			out.print("success");
			log.info("success");
			ChargeForm chargeForm = new ChargeForm();
			Date date = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			chargeForm.setUpdateTime(dateformat.format(date));
			chargeForm.setChargeId(returnForm.getOut_trade_no());
			chargeForm.setUpdateUser("支付宝");
			if ("TRADE_SUCCESS".equals(returnForm.getTrade_status())) {
				boolean result = chargeService.editCharge(chargeForm);
				if (!result) {
					throw new SQLException("付款标记修改失败！");
				}
			}
		} else {
			out.print("fail");
			log.info("fail");
		}
	}
}
