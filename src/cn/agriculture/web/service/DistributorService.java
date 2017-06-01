package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.agriculture.common.util.MD5Util;
import cn.agriculture.web.form.DistributorForm;

@Service
public class DistributorService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<DistributorForm> searchDistributorList() {
		List<DistributorForm> result = queryDao.executeForObjectList("Distributor.selectDistributorList", null);
		return result;
	}
	
	public DistributorForm searchDistributor(DistributorForm distributorForm) {
		DistributorForm result = queryDao.executeForObject("Distributor.selectDistributor", distributorForm, DistributorForm.class);
		return result;
	}
	
	public DistributorForm searchDistributorForPrice(DistributorForm distributorForm) {
		DistributorForm result = queryDao.executeForObject("Distributor.selectDistributorForPrice", distributorForm, DistributorForm.class);
		return result;
	}
	
	public boolean addDistributor(DistributorForm frm) {
		if (StringUtils.isEmpty(frm.getPassword())) {
			frm.setPassword(MD5Util.getMD5("111111"));
		} else {
			frm.setPassword(MD5Util.getMD5(frm.getPassword()));
		}
		
		int result = updateDao.execute("Distributor.addDistributor", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editDistributor(DistributorForm frm) {
		frm.setPassword(MD5Util.getMD5("111111"));
		int result = updateDao.execute("Distributor.editDistributor", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delDistributor(DistributorForm frm) {
		int result = updateDao.execute("Distributor.delDistributor", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editDistributorPassword(DistributorForm frm) {
		frm.setPassword(MD5Util.getMD5(frm.getPasswordNew()));
		int result = updateDao.execute("Distributor.editDistributorPassword", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editDistributorAmount(DistributorForm frm) {
		int result = updateDao.execute("Distributor.editDistributorAmount", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
