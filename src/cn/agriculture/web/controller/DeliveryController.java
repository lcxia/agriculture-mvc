package cn.agriculture.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.DeliveryForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.DeliveryService;

@Slf4j
@Controller("DeliveryController")
@RequestMapping("/")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;
	
    @RequestMapping(value = "initDelivery", method = RequestMethod.GET)
    public String initDelivery(Model model) {
    	log.info("销售订单列表初始化");
    	model.addAttribute("list", deliveryService.searchDeliveryList());
        return "manager/delivery/deliveryList";
    }
    
    @RequestMapping(value = "submitDelivery", method = RequestMethod.GET)
    public String executeSubmitDelivery(Model model, HttpSession session, DeliveryForm deliveryForm) {
    	log.info("发货");
    	UVO uvo = (UVO)session.getAttribute("UVO");
    	deliveryForm.setUpdateUser(uvo.getUserName());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		deliveryForm.setUpdateTime(dateformat.format(date));
    	deliveryService.submitDelivery(deliveryForm);
    	model.addAttribute("list", deliveryService.searchDeliveryList());
        return "manager/delivery/deliveryList";
    }
}
