package cn.agriculture.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.SpecialForm;

@Service
@PropertySource("classpath:system.properties")
public class SpecialService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	@Autowired
	private Environment env;
	
	public List<SpecialForm> searchSpecial() {
		List<SpecialForm> result = queryDao.executeForObjectList("Special.selectSpecial", null);
		return result;
	}
	
	public void delSpecial() {
		updateDao.execute("Special.deleteSpecial", null);
	}
	
	public boolean checkIp(SpecialForm frm, String remoteIp) {
		SpecialForm specialForm = queryDao.executeForObject("Special.selectSpecialPrice", frm, SpecialForm.class);
		if (Double.valueOf(specialForm.getRetailPrice()) <= 1d) {
			return false;
		}
		Integer count = queryDao.executeForObject("Special.selectRemoteIp", remoteIp, Integer.class);
		if (count != 0) {
			return false;
		}
		int result = updateDao.execute("Special.addRemoteIp", remoteIp);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean  cutPrice(SpecialForm specialForm) {
		int result = updateDao.execute("Special.cutPrice", specialForm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public SpecialForm searchAlipay(SpecialForm frm) {
		SpecialForm specialForm = queryDao.executeForObject("Special.selectSpecialPrice", frm, SpecialForm.class);
		specialForm.setCommodityId(specialForm.getCommodityId());
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		specialForm.setOutTradeNo(env.getProperty("id.prefix.special") + dateformat.format(date));
		specialForm.setSubject("特别活动促销的商品订单");
		specialForm.setPrice(specialForm.getRetailPrice());
		specialForm.setBody(specialForm.getCommodityName());
		String host = env.getProperty("host.mobile");
		specialForm.setShowUrl(host + "/initSpecialAlipayComfirm?commodityId=" + specialForm.getCommodityId());
		return specialForm;
	}
	
	public boolean editStock(SpecialForm frm) {
		Integer stock = queryDao.executeForObject("Special.selectStock", frm, Integer.class);
		if (stock == 0) {
			return false;
		}
		int result = updateDao.execute("Special.editStock", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean addAlipayHistory(SpecialForm frm) {
		// 做成支付宝历史单据
		int result = updateDao.execute("Special.addAlipayHistory", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
