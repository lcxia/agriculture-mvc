package cn.agriculture.common.component;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.Item;

@Service
public class ItemListComponent {

	@Autowired
	QueryDAO queryDao;
	
	public List<Item> getProvinceList() {
		return queryDao.executeForObjectList("Common.selectProvince", null);
	}
	
	public List<Item> getProvinceList1(String expressId) {
		return queryDao.executeForObjectList("Common.selectExpressPriceProvince", expressId);
	}
	
	
	public String getProvinceLabel(String provinceValue) {
		return queryDao.executeForObject("Common.selectProvinceLabel", provinceValue, String.class);
	}
	
	public List<Item> getCityList(String provinceId) {
		return queryDao.executeForObjectList("Common.selectCity", provinceId);
	}
	
	public String getCityLabel(String cityValue) {
		return queryDao.executeForObject("Common.selectCityLabel", cityValue, String.class);
	}
	
	public List<Item> getCommodityType() {
		return queryDao.executeForObjectList("Common.selectCommodityType", null);
	}
	
	public List<Item> getSupplierList() {
		return queryDao.executeForObjectList("Common.selectSupplier", null);
	}
	
	public String getSupplierLabel(String supplierValue) {
		return queryDao.executeForObject("Common.selectSupplierLabel", supplierValue, String.class);
	}
	
	public List<Item> getBrandList(String supplierId) {
		return queryDao.executeForObjectList("Common.selectBrand", supplierId);
	}
	
	public String getBrandLabel(String brandValue) {
		return queryDao.executeForObject("Common.selectBrandLabel", brandValue, String.class);
	}
	
	public List<Item> getCommodityList() {
		return queryDao.executeForObjectList("Common.selectCommodity", null);
	}
	
	
	public List<Item> getSurCommodityList() {
		return queryDao.executeForObjectList("Common.selectSurCommodity", null);
	}
	
	public List<Item> getCommodityList(String brandId) {
		return queryDao.executeForObjectList("Common.selectCommodity", brandId);
	}
	
	public String getCommodityLabel(String commodityValue) {
		return queryDao.executeForObject("Common.selectCommodityLabel", commodityValue, String.class);
	}
	
	public List<Item> getExpressList() {
		return queryDao.executeForObjectList("Common.selectExpress", null);
	}
	
	public List<Item> getDistributorList() {
		return queryDao.executeForObjectList("Common.selectDistributor", null);
	}
}
