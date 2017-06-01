package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.AlipayReportForm;

@Service
public class AlipayReportService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	public List<AlipayReportForm> searchAlipayReportList() {
		List<AlipayReportForm> result = queryDao.executeForObjectList("AlipayReport.selectAlipayReportList", null);
		return result;
	}
	
	public boolean delAlipayReport(AlipayReportForm frm) {
		
		int result = updateDao.execute("AlipayReport.deleteAlipayReport", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
}
