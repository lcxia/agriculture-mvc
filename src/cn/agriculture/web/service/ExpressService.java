package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.ExpressForm;
import cn.agriculture.web.form.ExpressPriceForm;

@Service
public class ExpressService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<ExpressForm> searchExpressList() {
		List<ExpressForm> result = queryDao.executeForObjectList("Express.selectExpressList", null);
		return result;
	}
	
	public ExpressForm searchExpress(ExpressForm frm) {
		ExpressForm result = queryDao.executeForObject("Express.selectExpress", frm, ExpressForm.class);
		return result;
	}
	
	public boolean addExpress(ExpressForm frm) {
		Integer sequee = queryDao.executeForObject("Express.getSeq", null, Integer.class);
		String expressId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setExpressId(expressId);
		int result = updateDao.execute("Express.addExpress", frm);
		if (result == 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean editExpress(ExpressForm frm) {
		int result = updateDao.execute("Express.editExpress", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delExpress(ExpressForm frm) {
		int result = updateDao.execute("Express.delExpress", frm);
		if (result == 1) {
			ExpressPriceForm expressPriceForm = new ExpressPriceForm();
			expressPriceForm.setExpressId(frm.getExpressId());
			updateDao.execute("ExpressPrice.delExpressPrice", expressPriceForm);
			return true;
		}
		return false;
	}
}
