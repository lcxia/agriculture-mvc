package cn.agriculture.web.service;

import java.math.BigDecimal;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.agriculture.web.form.DistributorPriceForm;

@Service
public class DistributorPriceService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<DistributorPriceForm> searchDistributorPriceList(DistributorPriceForm frm) {
		List<DistributorPriceForm> result = queryDao.executeForObjectList("DistributorPrice.selectDistributorPriceList", frm);
		for (DistributorPriceForm distributorPriceForm : result) {
			BigDecimal benchmarkPrice = new BigDecimal(Double.valueOf(distributorPriceForm.getBenchmarkPrice()) * (1 + Double.valueOf(frm.getPriceIncrement())));
			distributorPriceForm.setBenchmarkPrice(String.valueOf(benchmarkPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
			
			BigDecimal guidePrice = new BigDecimal(Double.valueOf(distributorPriceForm.getGuidePrice()));
			distributorPriceForm.setGuidePrice(String.valueOf(guidePrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
			
			BigDecimal retailPrice = new BigDecimal(Double.valueOf(distributorPriceForm.getRetailPrice()));
			distributorPriceForm.setRetailPrice(String.valueOf(retailPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
		}
		return result;
	}
	
	public DistributorPriceForm searchDistributorPrice(DistributorPriceForm frm) {
		DistributorPriceForm result = queryDao.executeForObject("DistributorPrice.selectDistributorPrice", frm, DistributorPriceForm.class);
		BigDecimal guidePrice = new BigDecimal(Double.valueOf(result.getGuidePrice()));
		result.setGuidePrice(String.valueOf(guidePrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
		if (!StringUtils.isEmpty(result.getRetailPrice())) {
			BigDecimal retailPrice = new BigDecimal(Double.valueOf(result.getRetailPrice()));
			result.setRetailPrice(String.valueOf(retailPrice.setScale(2, BigDecimal.ROUND_HALF_UP)));
		}
		return result;
	}
	
	public boolean editDistributorPrice(DistributorPriceForm frm) {
		DistributorPriceForm distributorPriceForm = queryDao.executeForObject("DistributorPrice.selectDistributorPrice", frm, DistributorPriceForm.class);
		if (Double.valueOf(distributorPriceForm.getGuidePrice()) > Double.valueOf(frm.getRetailPrice())) {
			return false;
		}
		if (StringUtils.isEmpty(distributorPriceForm.getDistributorPriceId())) {
			Integer sequee = queryDao.executeForObject("DistributorPrice.getSeq", null, Integer.class);
			String distributorPriceId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
			frm.setDistributorPriceId(distributorPriceId);
			int result = updateDao.execute("DistributorPrice.addDistributorPrice", frm);
			if (result == 1) {
				return true;
			}
		} else {
			int result = updateDao.execute("DistributorPrice.editDistributorPrice", frm);
			if (result == 1) {
				return true;
			}
		}
		return false;
	}
}
