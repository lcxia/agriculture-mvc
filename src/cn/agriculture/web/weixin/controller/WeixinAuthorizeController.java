package cn.agriculture.web.weixin.controller;

import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weixin.popular.api.SnsAPI;
import weixin.popular.api.TicketAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.SnsToken;
import weixin.popular.bean.Ticket;
import weixin.popular.bean.Token;
import weixin.popular.bean.User;
import cn.agriculture.web.form.CartForm;
import cn.agriculture.web.form.GoodsForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.CartService;
import cn.agriculture.web.service.GoodsService;

@Slf4j
@Controller("WeixinAuthorizeController")
@RequestMapping("/")
@PropertySource("classpath:system.properties")
public class WeixinAuthorizeController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	CartService cartService;
	
	private final static String APP_ID = "";//公众号的ID
	
	private final static String SECRET = "";//秘钥
	
	@RequestMapping(value = "initWeixin", method = RequestMethod.GET)
    public String init(Model model, String code, String state) {
		log.info("微信网页授权初始化");
		return "mobile/index";
	}
	@RequestMapping(value = "initShare", method = RequestMethod.GET)
    public String initShare(Model model, String code, String state, HttpServletRequest request, HttpSession session, Device device) throws Exception {
		log.info("微信网页授权初始化");
		User user = getWinxinUser(code);
		model.addAttribute("nickname", user.getNickname());
		
		log.info("微信网页JSSDK初始化");
		StringBuffer homeUrl = request.getRequestURL();
        String queryString =request.getQueryString();
        if(!StringUtils.isEmpty(queryString)){
            homeUrl.append("?").append(queryString);
        }
		long timestamp = System.currentTimeMillis() / 1000;
		String nonceStr = UUID.randomUUID().toString();
		
		Token token = TokenAPI.token(APP_ID, SECRET);
		String accessToken = token.getAccess_token();
		Ticket ticket = TicketAPI.ticketGetticket(accessToken);

		String signature = getSignature(ticket.getTicket(), nonceStr, timestamp, homeUrl.toString());
				
		model.addAttribute("appid",  APP_ID);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonceStr", nonceStr);
		model.addAttribute("signature", signature);
		
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
	
	private User getWinxinUser(String code) throws Exception {

        // 通过code换取网页授权access_token
        SnsToken snsToken = SnsAPI.oauth2AccessToken(APP_ID, SECRET, code);
        if (!StringUtils.isEmpty(snsToken.getErrcode())) {
            log.info("SnsAPI.oauth2AccessToken 失败 Errcode：" + snsToken.getErrcode() + ",errmsg:" + snsToken.getErrmsg());
            throw new Exception();
        }
        String openId = snsToken.getOpenid();
        // 取得基础access_token
        //Token token = TokenAPI.token(APP_ID, SECRET);
        //if (!StringUtils.isEmpty(token.getErrcode())) {
        //    log.info("TokenAPI.token 失败 Errcode：" + token.getErrcode() + ",errmsg:" + token.getErrmsg());
        //    throw new Exception();
        //}
        //String accessToken = token.getAccess_token();
        // 获取用户基本信息
        //User user = UserAPI.userInfo(accessToken, openId);
        // 拉取用户信息
        User user = SnsAPI.userinfo(snsToken.getAccess_token(), openId, "zh_CN");
        if (!StringUtils.isEmpty(user.getErrcode())) {
            log.info("UserAPI.userInfo 失败 Errcode：" + user.getErrcode() + ",errmsg:" + user.getErrmsg());
            throw new Exception();
        }
        
        return user;
    }
	
	/**
     * 获得分享链接的签名。
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws Exception
     */
    public static String getSignature(String ticket, String nonceStr, long timeStamp, String url) throws Exception {
        String sKey = "jsapi_ticket=" + ticket
                + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp
                + "&url=" + url;
        return getSignature(sKey);
    }
    
    /**
     * 验证签名。
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String getSignature(String sKey) throws Exception {
        String ciphertext = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(sKey.toString().getBytes());
        ciphertext = byteToStr(digest);
        return ciphertext.toLowerCase();
    }
 
 /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */ 
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */ 
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
   
        String s = new String(tempArr);  
        return s;  
    }
}
