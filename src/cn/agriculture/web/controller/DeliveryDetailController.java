package cn.agriculture.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.DeliveryDetailForm;
import cn.agriculture.web.form.DeliveryForm;
import cn.agriculture.web.service.DeliveryDetailService;
import cn.agriculture.web.service.DeliveryService;

@Slf4j
@Controller("DeliveryDetailController")
@RequestMapping("/")
public class DeliveryDetailController {

	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	DeliveryDetailService deliveryDetailService;
	
    @RequestMapping(value = "initDeliveryDetail", method = RequestMethod.GET)
    public String initPlaceOrderDetail(Model model, HttpSession session, DeliveryDetailForm deliveryDetailForm) {
    	log.info("销售订单明细列表初始化");
    	DeliveryForm deliveryForm = new DeliveryForm();
    	deliveryForm.setPlaceOrderId(deliveryDetailForm.getPlaceOrderId());
    	DeliveryForm result = deliveryService.searchDeliveryForDetail(deliveryForm);
    	if (result == null) {
    		model.addAttribute("list", deliveryService.searchDeliveryList());
    		model.addAttribute("message", "请先登记快递单号！");
            return "manager/delivery/deliveryList";
    	}
		model.addAttribute("deliveryForm", result);
		List<DeliveryDetailForm> list = deliveryDetailService.searchDeliveryDetailList(deliveryDetailForm);
		model.addAttribute("list", list);
		Double weight = 0d;
		for (DeliveryDetailForm item : list) {
			weight = weight + Double.valueOf(item.getSumWeight());
		}
		//weight = weight - Double.valueOf(result.getSeparateWeight());
		//if (weight < 0) {
		//	weight = 0d;
		//}
		//Double expressPrice =Double.valueOf(result.getFirstHeavyPrice()) + Math.ceil(weight/Double.valueOf(result.getSeparateWeight())) * Double.valueOf(result.getContinuedHeavyPrice());
		if (!"".equals(result.getExpressListId())) {
			Double expressPrice = 0d;
			weight = Math.ceil(Double.valueOf(weight)/1000);
			Double separateWeight = Double.valueOf(result.getSeparateWeight())/1000;
			if(weight >= separateWeight) {
				// 实际重量大于等于分割重量时，按照实际重量*续重价格计算
				expressPrice = weight*Double.valueOf(result.getContinuedHeavyPrice());
			} else {
				// 实际重量小于分割重量时，按照首重价格+(实际重量-1)*续重价格计算
				if ((weight-1) < 0d) {
					weight = 0d;
				}
				expressPrice = Double.valueOf(result.getFirstHeavyPrice())+(weight-1)*Double.valueOf(result.getContinuedHeavyPrice());
			}
			
			result.setExpressPrice(String.valueOf(expressPrice));
		} else {
			result.setExpressPrice("");
		}
		
		return "manager/deliveryDetail/deliveryDetailList";
    }
}
