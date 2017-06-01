package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.StockForm;

@Service
public class StockService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<StockForm> searchStockList() {
		List<StockForm> result = queryDao.executeForObjectList("Stock.selectStockList", null);
		return result;
	}
	
	public StockForm searchStock(StockForm frm) {
		StockForm result = queryDao.executeForObject("Stock.selectStock", frm, StockForm.class);
		return result;
	}
	
	public boolean addStock(StockForm frm) {
		Integer sequee = queryDao.executeForObject("Stock.getSeq", null, Integer.class);
		String stockId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setStockId(stockId);
		int result = updateDao.execute("Stock.addStock", frm);
		if (result == 1) {
			return true;
		}
		
		return false;
	}
	
	public StockForm getEditCommodity(StockForm frm) {
		return queryDao.executeForObject("Stock.selectEditCommodity", frm,StockForm.class);
	}
	
	public boolean editStock(StockForm frm) {
		int result = updateDao.execute("Stock.editStock", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delStock(StockForm frm) {
		int result = updateDao.execute("Stock.delStock", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
