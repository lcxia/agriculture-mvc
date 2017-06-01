package cn.agriculture.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import cn.agriculture.web.form.AlipayForm;
import cn.agriculture.web.form.DistributorPriceForm;
import cn.agriculture.web.form.ReturnForm;

@Service
@PropertySource("classpath:system.properties")
public class AlipayService {

	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	UpdateDAO updateDao;
	
	@Autowired
	private Environment env;
	
	public AlipayForm searchAlipay(AlipayForm frm) {
		DistributorPriceForm distributorPriceForm = queryDao.executeForObject("Alipay.selectDistributorPrice", frm, DistributorPriceForm.class);
		AlipayForm alipayForm = new AlipayForm();
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		alipayForm.setOutTradeNo(distributorPriceForm.getDistributorId() + dateformat.format(date));
		alipayForm.setSubject(distributorPriceForm.getDistributorId() +"推荐的商品订单");
		Double price = Double.valueOf(distributorPriceForm.getRetailPrice());
		// 不满88元加8元邮费
		if (price < 88) {
			price = price + 8;
		}
		alipayForm.setPrice(String.valueOf(price));
		alipayForm.setBody(distributorPriceForm.getCommodityName());
		String host = env.getProperty("host.mobile");
		alipayForm.setShowUrl(host + "/initDistributorAlipayComfirm?distributorPriceId=" + distributorPriceForm.getDistributorPriceId());
		//alipayForm.setShowUrl("http://localhost:8080/agriculture-mvc/initDistributorAlipayComfirm?distributorPriceId=" + distributorPriceForm.getDistributorPriceId());
		alipayForm.setGuestId(distributorPriceForm.getDistributorId());
		alipayForm.setCommodityId(distributorPriceForm.getCommodityId());
		alipayForm.setDistributorName(distributorPriceForm.getDistributorName());
		alipayForm.setDistributorPriceId(distributorPriceForm.getDistributorPriceId());
		return alipayForm;
	}
	
	public boolean editStock(AlipayForm frm) {
		Integer stock = queryDao.executeForObject("Alipay.selectStock", frm, Integer.class);
		if (stock < Integer.valueOf(frm.getCount())) {
			return false;
		}
		int result = updateDao.execute("Alipay.editStock", frm);
		if (result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean editPayment(ReturnForm frm) {
		if ("TRADE_SUCCESS".equals(frm.getTrade_status())) {
			int result = updateDao.execute("Alipay.editPayment", frm);
			if (result == 1) {
				return true;
			}
		}
		return false;
	}
}
