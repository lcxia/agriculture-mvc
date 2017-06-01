package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.ExpressPriceForm;

@Service
public class ExpressPriceService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<ExpressPriceForm> searchExpressPriceList(ExpressPriceForm frm) {
		List<ExpressPriceForm> result = queryDao.executeForObjectList("ExpressPrice.selectExpressPriceList", frm);
		return result;
	}
	
	public ExpressPriceForm searchExpressPrice(ExpressPriceForm frm) {
		ExpressPriceForm result = queryDao.executeForObject("ExpressPrice.selectExpressPrice", frm, ExpressPriceForm.class);
		return result;
	}
	
	public boolean addExpressPrice(ExpressPriceForm frm) {
		Integer sequee = queryDao.executeForObject("ExpressPrice.getSeq", null, Integer.class);
		String expressPriceId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setExpressPriceId(expressPriceId);
		int result = updateDao.execute("ExpressPrice.addExpressPrice", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editExpressPrice(ExpressPriceForm frm) {
		int result = updateDao.execute("ExpressPrice.editExpressPrice", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delExpressPrice(ExpressPriceForm frm) {
		int result = updateDao.execute("ExpressPrice.delExpressPrice", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
