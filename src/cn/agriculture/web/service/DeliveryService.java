package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.DeliveryForm;
import cn.agriculture.web.form.ExpressListForm;
import cn.agriculture.web.form.ExpressPriceForm;

@Service
public class DeliveryService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<DeliveryForm> searchDeliveryList() {
		List<DeliveryForm> result = queryDao.executeForObjectList("Delivery.selectDeliveryList", null);
		return result;
	}
	
	public DeliveryForm searchDeliveryForDetail(DeliveryForm frm) {
		DeliveryForm result = queryDao.executeForObject("Delivery.selectDeliveryForDetail", frm, DeliveryForm.class);
		//取得快递单号
		if (result.getExpressListId() == null) {
			List<ExpressListForm> expressList = queryDao.executeForObjectList("Delivery.selectExpressListList", result);
			if (expressList == null || expressList.size() == 0) {
				result.setExpressListId("");
			} else {
				result.setExpressListId(expressList.get(0).getExpressListId());
				ExpressListForm expressListForm = queryDao.executeForObject("Delivery.selectExpressList", result.getExpressListId(), ExpressListForm.class);
				ExpressPriceForm param = new ExpressPriceForm();
				param.setExpressId(expressListForm.getExpressId());
				param.setProvinceId(result.getProvinceId());
				ExpressPriceForm expressPriceForm = queryDao.executeForObject("Delivery.selectExpressPrice", param, ExpressPriceForm.class);
				result.setFirstHeavyPrice(expressPriceForm.getFirstHeavyPrice());
				result.setContinuedHeavyPrice(expressPriceForm.getContinuedHeavyPrice());
				result.setSeparateWeight(expressPriceForm.getSeparateWeight());
			}
		} else {
			ExpressListForm expressListForm = queryDao.executeForObject("Delivery.selectExpressList", result.getExpressListId(), ExpressListForm.class);
			ExpressPriceForm param = new ExpressPriceForm();
			param.setExpressId(expressListForm.getExpressId());
			param.setProvinceId(result.getProvinceId());
			ExpressPriceForm expressPriceForm = queryDao.executeForObject("Delivery.selectExpressPrice", param, ExpressPriceForm.class);
			result.setFirstHeavyPrice(expressPriceForm.getFirstHeavyPrice());
			result.setContinuedHeavyPrice(expressPriceForm.getContinuedHeavyPrice());
			result.setSeparateWeight(expressPriceForm.getSeparateWeight());
		}
		return result;
	}
	
	public boolean submitDelivery(DeliveryForm frm) {
		frm.setStatus("已发货");
		int result = updateDao.execute("Delivery.submitDelivery", frm);
		if (result == 1) {
			frm.setIsUsed("已使用");
			updateDao.execute("Delivery.editExpressList", frm);
			return true;
		}
		return false;
	}
}
