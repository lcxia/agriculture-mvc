package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.ChargeForm;

@Service
@PropertySource("classpath:system.properties")
public class ChargeService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	@Autowired
	private Environment env;
	
	public List<ChargeForm> searchChargeList(ChargeForm frm) {
		List<ChargeForm> result = queryDao.executeForObjectList("Charge.selectChargeList", frm);
		return result;
	}
	
	public boolean addCharge(ChargeForm frm) {
		Integer sequee = queryDao.executeForObject("Charge.getSeq", null, Integer.class);
		String chargeId = env.getProperty("id.prefix.charge") + frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setChargeId(chargeId);
		frm.setStatus("已送审");
		int result = updateDao.execute("Charge.addCharge", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editCharge(ChargeForm frm) {
		frm.setStatus("已支付");
		int result = updateDao.execute("Charge.editCharge", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
