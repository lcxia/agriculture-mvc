package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.ChargeConfirmForm;
import cn.agriculture.web.form.DistributorForm;

@Service
public class ChargeConfirmService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<ChargeConfirmForm> searchChargeConfirmList() {
		List<ChargeConfirmForm> result = queryDao.executeForObjectList("ChargeConfirm.selectChargeConfirmList", null);
		return result;
	}
	
	public boolean editChargeConfirm(ChargeConfirmForm frm) {
		ChargeConfirmForm chargeConfirmForm = queryDao.executeForObject("ChargeConfirm.selectChargeConfirm", frm, ChargeConfirmForm.class);
		DistributorForm distributorForm = queryDao.executeForObject("ChargeConfirm.selectDistributor", chargeConfirmForm, DistributorForm.class);
		if(distributorForm.getAmount() == null) {
			distributorForm.setAmount("0");
		}
		distributorForm.setAmount(String.valueOf(Double.valueOf(distributorForm.getAmount()) + Double.valueOf(chargeConfirmForm.getAmount())));
		updateDao.execute("ChargeConfirm.editDistributor", distributorForm);
		frm.setStatus("已充值");
		int result = updateDao.execute("ChargeConfirm.editChargeConfirm", frm);
		if (result == 1) {
			
			return true;
		}
		return false;
	}
}
