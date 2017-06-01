package cn.agriculture.web.service;

import java.math.BigDecimal;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.DeliveryDetailForm;

@Service
public class DeliveryDetailService {
	
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<DeliveryDetailForm> searchDeliveryDetailList(DeliveryDetailForm frm) {
		List<DeliveryDetailForm> result = queryDao.executeForObjectList("DeliveryDetail.selectDeliveryDetailList", frm);
		for(DeliveryDetailForm deliveryDetailForm : result) {
			BigDecimal benchmarkPrice = new BigDecimal(Double.valueOf(deliveryDetailForm.getBenchmarkPrice()) * (1 + Double.valueOf(deliveryDetailForm.getPriceIncrement())));
			deliveryDetailForm.setBenchmarkPrice(String.valueOf(benchmarkPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
			BigDecimal sumAmount = new BigDecimal(Double.valueOf(deliveryDetailForm.getBenchmarkPrice()) * Double.valueOf(deliveryDetailForm.getPurchaseCount()));
			deliveryDetailForm.setSumAmount(String.valueOf(sumAmount.setScale(2, BigDecimal.ROUND_HALF_UP)));
			BigDecimal sumWeight = new BigDecimal(Double.valueOf(deliveryDetailForm.getWeight()) * Double.valueOf(deliveryDetailForm.getPurchaseCount()));
			deliveryDetailForm.setSumWeight(String.valueOf(sumWeight.setScale(2, BigDecimal.ROUND_HALF_UP)));
		}
		return result;
	}
}
