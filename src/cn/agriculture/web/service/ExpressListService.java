package cn.agriculture.web.service;

import java.math.BigDecimal;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.ExpressListForm;

@Service
public class ExpressListService {

	@Autowired
	QueryDAO queryDao;

	@Autowired
	UpdateDAO updateDao;

	public List<ExpressListForm> searchExpressListList() {
		List<ExpressListForm> result = queryDao.executeForObjectList(
				"ExpressList.selectExpressListList", null);
		return result;
	}

	public boolean addExpressList(ExpressListForm frm) {

		frm.setIsUsed("未使用");
		if (Double.valueOf(frm.getExpressListIdEnd()) - Double.valueOf(frm.getExpressListIdStart()) > 1000) {
			return false;
		}
		for (Double i = Double.valueOf(frm.getExpressListIdStart()); i <= Double
				.valueOf(frm.getExpressListIdEnd()); i++) {
			String expressListId = String.valueOf(BigDecimal.valueOf(i.doubleValue())).toString();
			frm.setExpressListId(expressListId);
			int result = updateDao.execute("ExpressList.addExpressList", frm);
			if (result != 1) {
				return false;
			}
		}
		return true;
	}
}
