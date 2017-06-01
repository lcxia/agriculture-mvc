package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.BrandForm;

@Service
public class BrandService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<BrandForm> searchBrand(BrandForm frm) {
		List<BrandForm> result = queryDao.executeForObjectList("Brand.selectBrand", frm);
		return result;
	}
	
	public boolean addBrand(BrandForm frm) {
		Integer sequee = queryDao.executeForObject("Brand.getSeq", null, Integer.class);
		String brandId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setBrandId(brandId);
		int result = updateDao.execute("Brand.addBrand", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editBrand(BrandForm frm) {
		int result = updateDao.execute("Brand.editBrand", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delBrand(BrandForm frm) {
		int result = updateDao.execute("Brand.delBrand", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
