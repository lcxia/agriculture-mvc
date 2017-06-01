package cn.agriculture.web.service;

import java.math.BigDecimal;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.RebateForm;

@Service
public class RebateService {

	@Autowired
	QueryDAO queryDao;

	@Autowired
	UpdateDAO updateDao;
	
	public List<RebateForm> searchRebateList() {
		List<RebateForm> result = queryDao.executeForObjectList("Rebate.selectRebateList", null);
		for (RebateForm rebateForm : result) {
			Double benchmarkPrice = Double.valueOf(rebateForm.getBenchmarkPrice());
			Double priceIncrement = 1 + Double.valueOf(rebateForm.getPriceIncrement());
			benchmarkPrice = benchmarkPrice * priceIncrement;
			BigDecimal benchmarkPriceFin = new BigDecimal(benchmarkPrice);
			rebateForm.setBenchmarkPrice(String.valueOf(benchmarkPriceFin.setScale(2, BigDecimal.ROUND_HALF_UP)));
			Double retailPrice = Double.valueOf(rebateForm.getRetailPrice());
			Double rebateValue = retailPrice - benchmarkPrice;
			BigDecimal rebateValueFin = new BigDecimal(rebateValue);
			rebateForm.setRebateValue(String.valueOf(rebateValueFin.setScale(2, BigDecimal.ROUND_HALF_UP)));
		}
		return result;
	}
	
	public boolean editRebate(RebateForm frm) {
		Double amount = Double.valueOf(frm.getAmount());
		Double rebateValue = Double.valueOf(frm.getRebateValue());
		amount = amount + rebateValue;
		BigDecimal amountFin = new BigDecimal(amount);
		frm.setAmount(String.valueOf(amountFin.setScale(2, BigDecimal.ROUND_HALF_UP)));
		int result = updateDao.execute("Rebate.editAmount", frm);
		if (result == 1) {
			updateDao.execute("Rebate.editRebate", frm);
			return true;
		}
		return false;
	}
}
