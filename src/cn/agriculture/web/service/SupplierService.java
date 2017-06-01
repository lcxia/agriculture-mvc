package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.BrandForm;
import cn.agriculture.web.form.SupplierForm;

@Service
public class SupplierService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<SupplierForm> searchSupplier(SupplierForm frm) {
		List<SupplierForm> result = queryDao.executeForObjectList("Supplier.selectSupplier", frm);
		return result;
	}
	
	public boolean addSupplier(SupplierForm frm) {
		Integer sequee = queryDao.executeForObject("Supplier.getSeq", null, Integer.class);
		String supplierId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setSupplierId(supplierId);
		int result = updateDao.execute("Supplier.addSupplier", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editSupplier(SupplierForm frm) {
		int result = updateDao.execute("Supplier.editSupplier", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delSupplier(SupplierForm frm) {
		int result = updateDao.execute("Supplier.delSupplier", frm);
		if (result == 1) {
			BrandForm brandForm = new BrandForm();
			brandForm.setSupplierId(frm.getSupplierId());
			updateDao.execute("Brand.delBrand", brandForm);
			return true;
		}
		return false;
	}
}
