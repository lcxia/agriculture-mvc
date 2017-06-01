package cn.agriculture.web.service;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.CityForm;

@Service
public class CityService {
	
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public boolean addCity(CityForm frm) {
		CityForm cityForm = queryDao.executeForObject("City.selectCity", frm, CityForm.class);
		Integer cityId = Integer.valueOf(cityForm.getCityId()) + 1;
		frm.setCityId(String.format("%010d", cityId));
		int result = updateDao.execute("City.addCity", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
