package cn.agriculture.web.service;

import java.math.BigDecimal;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.PlaceOrderDetailForm;

@Service
public class PlaceOrderDetailService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<PlaceOrderDetailForm> searchPlaceOrderDetailList(PlaceOrderDetailForm frm) {
		List<PlaceOrderDetailForm> result = queryDao.executeForObjectList("PlaceOrderDetail.selectPlaceOrderDetailList", frm);
		for(PlaceOrderDetailForm placeOrderDetailForm : result) {
			BigDecimal benchmarkPrice = new BigDecimal(Double.valueOf(placeOrderDetailForm.getBenchmarkPrice()) * (1 + Double.valueOf(frm.getPriceIncrement())));
			placeOrderDetailForm.setBenchmarkPrice(String.valueOf(benchmarkPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
			BigDecimal sumAmount = new BigDecimal(Double.valueOf(placeOrderDetailForm.getBenchmarkPrice()) * Double.valueOf(placeOrderDetailForm.getPurchaseCount()));
			placeOrderDetailForm.setSumAmount(String.valueOf(sumAmount.setScale(2, BigDecimal.ROUND_HALF_UP)));
			BigDecimal sumWeight = new BigDecimal(Double.valueOf(placeOrderDetailForm.getWeight()) * Double.valueOf(placeOrderDetailForm.getPurchaseCount()));
			placeOrderDetailForm.setSumWeight(String.valueOf(sumWeight.setScale(2, BigDecimal.ROUND_HALF_UP)));
		}
		return result;
	}
	
	public PlaceOrderDetailForm searchPlaceOrderDetail(PlaceOrderDetailForm frm) {
		PlaceOrderDetailForm result = queryDao.executeForObject("PlaceOrderDetail.selectPlaceOrderDetail", frm, PlaceOrderDetailForm.class);
		BigDecimal benchmarkPrice = new BigDecimal(Double.valueOf(result.getBenchmarkPrice()) * (1 + Double.valueOf(frm.getPriceIncrement())));
		result.setBenchmarkPrice(String.valueOf(benchmarkPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
		return result;
	}
	
	public boolean addPlaceOrderDetail(PlaceOrderDetailForm frm) {
		Integer sequee = queryDao.executeForObject("PlaceOrderDetail.getSeq", null, Integer.class);
		String placeOrderDetailId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setPlaceOrderDetailId(placeOrderDetailId);
		int result = updateDao.execute("PlaceOrderDetail.addPlaceOrderDetail", frm);
		if (result == 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean editPlaceOrderDetail(PlaceOrderDetailForm frm) {
		int result = updateDao.execute("PlaceOrderDetail.editPlaceOrderDetail", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delPlaceOrderDetail(PlaceOrderDetailForm frm) {
		int result = updateDao.execute("PlaceOrderDetail.delPlaceOrderDetail", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public PlaceOrderDetailForm searchCommodityAndStock(String commodityId, String priceIncrement) {
		PlaceOrderDetailForm result = queryDao.executeForObject("PlaceOrderDetail.selectCommodityAndStock", commodityId, PlaceOrderDetailForm.class);
		BigDecimal benchmarkPrice = new BigDecimal(Double.valueOf(result.getBenchmarkPrice()) * (1 + Double.valueOf(priceIncrement)));
		result.setBenchmarkPrice(String.valueOf(benchmarkPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
		return result;
	}
}
