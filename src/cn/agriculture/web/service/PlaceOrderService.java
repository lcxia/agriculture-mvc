package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.ExpressPriceForm;
import cn.agriculture.web.form.PlaceOrderDetailForm;
import cn.agriculture.web.form.PlaceOrderForm;
import cn.agriculture.web.form.StockForm;

@Service
public class PlaceOrderService {
	
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<PlaceOrderForm> searchPlaceOrderList(PlaceOrderForm frm) {
		List<PlaceOrderForm> result = queryDao.executeForObjectList("PlaceOrder.selectPlaceOrderList", frm);
		return result;
	}
	
	public PlaceOrderForm searchPlaceOrder(PlaceOrderForm frm) {
		PlaceOrderForm result = queryDao.executeForObject("PlaceOrder.selectPlaceOrder", frm, PlaceOrderForm.class);
		return result;
	}
	
	public PlaceOrderForm searchPlaceOrderForDetail(PlaceOrderForm frm) {
		PlaceOrderForm result = queryDao.executeForObject("PlaceOrder.selectPlaceOrderForDetail", frm, PlaceOrderForm.class);
		return result;
	}
	
	public boolean addPlaceOrder(PlaceOrderForm frm) {
		Integer sequee = queryDao.executeForObject("PlaceOrder.getSeq", null, Integer.class);
		String placeOrderId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setPlaceOrderId(placeOrderId);
		int result = updateDao.execute("PlaceOrder.addPlaceOrder", frm);
		if (result == 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean editPlaceOrder(PlaceOrderForm frm) {
		if (!"0".equals(frm.getWeight())) {
			// 计算运费
			ExpressPriceForm expressPriceForm = queryDao.executeForObject("PlaceOrder.selectExpressPrice", frm, ExpressPriceForm.class);
			//Double separate = Double.valueOf(frm.getWeight())/Double.valueOf(expressPriceForm.getSeparateWeight());
			//separate = Math.ceil(separate) - 1;
			//Double expressPrice = Double.valueOf(expressPriceForm.getFirstHeavyPrice()) + separate*Double.valueOf(expressPriceForm.getContinuedHeavyPrice());
			if (expressPriceForm != null) {
				Double expressPrice = 0d;
				Double weight = Math.ceil(Double.valueOf(frm.getWeight())/1000);
				Double separateWeight = Double.valueOf(expressPriceForm.getSeparateWeight())/1000;
				if(weight >= separateWeight) {
					// 实际重量大于等于分割重量时，按照实际重量*续重价格计算
					expressPrice = weight*Double.valueOf(expressPriceForm.getContinuedHeavyPrice());
				} else {
					// 实际重量小于分割重量时，按照首重价格+(实际重量-1)*续重价格计算
					if ((weight-1) < 0d) {
						weight = 0d;
					}
					expressPrice = Double.valueOf(expressPriceForm.getFirstHeavyPrice())+(weight-1)*Double.valueOf(expressPriceForm.getContinuedHeavyPrice());
				}
				frm.setAmount(String.valueOf(Double.valueOf(frm.getAmount()) + expressPrice));
			} else {
				frm.setAmount(String.valueOf(Double.valueOf(frm.getAmount())));
			}
			
		}
		int result = updateDao.execute("PlaceOrder.editPlaceOrder", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delPlaceOrder(PlaceOrderForm frm) {
		int result = updateDao.execute("PlaceOrder.delPlaceOrder", frm);
		if (result == 1) {
			PlaceOrderDetailForm placeOrderDetailForm = new PlaceOrderDetailForm();
			placeOrderDetailForm.setPlaceOrderId(frm.getPlaceOrderId());
			updateDao.execute("PlaceOrderDetail.delPlaceOrderDetail", placeOrderDetailForm);
			return true;
		}
		return false;
	}
	
	public boolean submitPlaceOrder(PlaceOrderForm frm) {
		int result = updateDao.execute("PlaceOrder.submitPlaceOrder", frm);
		if (result == 1) {
			PlaceOrderDetailForm placeOrderDetailForm = new PlaceOrderDetailForm();
			placeOrderDetailForm.setPlaceOrderId(frm.getPlaceOrderId());
			List<PlaceOrderDetailForm> list = queryDao.executeForObjectList("PlaceOrderDetail.selectPlaceOrderDetailList", placeOrderDetailForm);
			for (PlaceOrderDetailForm item : list) {
				StockForm stock = new StockForm();
				stock.setCommodityId(item.getCommodityId());
				if(Double.valueOf(item.getStock()) - Double.valueOf(item.getPurchaseCount()) < 0) {
					return false;
				}
				stock.setStock(String.valueOf(Double.valueOf(item.getStock()) - Double.valueOf(item.getPurchaseCount())));
				stock.setUpdateTime(frm.getUpdateTime());
				stock.setUpdateUser(frm.getUpdateUser());
				updateDao.execute("Stock.editStockForSubmitPlaceOrder", stock);
			}
			return true;
		}
		return false;
	}
}
