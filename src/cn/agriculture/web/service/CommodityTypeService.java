package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import net.sf.ehcache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.CommodityTypeForm;
@Service
public class CommodityTypeService {
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	@Autowired
	Cache pictureCache;
	public List<CommodityTypeForm> searchCommodityTypeList() {
		List<CommodityTypeForm> result = queryDao.executeForObjectList("CommodityType.selectCommodityTypeList", null);
		return result;
	}
	public CommodityTypeForm searchCommodityType(CommodityTypeForm frm){
		CommodityTypeForm result=queryDao.executeForObject("CommodityType.selectCommodityType",frm,CommodityTypeForm.class);
		return result;
	}
	public int updateCommodityType(CommodityTypeForm frm){
		int result=0;
		try {
			result = updateDao.execute("CommodityType.updateCommodityType", frm);
		} catch(Exception e) {
			return result;
		}
		return result;
	}
	public CommodityTypeForm getType(CommodityTypeForm frm){
		CommodityTypeForm result=queryDao.executeForObject("CommodityType.getType",frm,CommodityTypeForm.class);
       return result;
	}
	public int delCommodityType(CommodityTypeForm frm){
		int result =updateDao.execute("CommodityType.delCommodityType", frm);
		return result;
	}
	public int addCommodityType(CommodityTypeForm frm){
		Integer sequee = queryDao.executeForObject("CommodityType.getType", null, Integer.class);
		String commodityTypeId = frm.getUpdateTime().substring(0, 4) + String.format("%011d", sequee);
		frm.setCommodityTypeId(commodityTypeId);
		//异常捕捉
		int result=0;
		try {
			result = updateDao.execute("CommodityType.addCommodityType", frm);
		} catch(Exception e) {
			return result;
		}
		return result;
	}
	public CommodityTypeForm searchCommodityTypeName(CommodityTypeForm frm){
		CommodityTypeForm result =queryDao.executeForObject("CommodityType.selectCommodityTypeName", frm,CommodityTypeForm.class);
		return result;
	}
}
