package cn.agriculture.web.service;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.agriculture.common.util.QrCodeUtil;
import cn.agriculture.web.form.QrCodeForm;

@Service
@PropertySource("classpath:system.properties")
public class QrCodeService {
	
	@Autowired
	QueryDAO queryDao;
	
	@Autowired
	private Environment env;
	
	public List<QrCodeForm> searchQrCodeList(QrCodeForm frm) {
		List<QrCodeForm> result = queryDao.executeForObjectList("QrCode.selectQrCodeList", frm);
		return result;
	}
	
	public byte[] createQrCode(QrCodeForm frm) {
		String url;
		String host = env.getProperty("host.mobile");
		if (!StringUtils.isEmpty(frm.getDistributorPriceId())) {
			url = host + "/initDistributorAlipayComfirm?distributorPriceId=" + frm.getDistributorPriceId();
		} else {
			url = host + "/initSpecialAlipayComfirm?commodityId=" + frm.getCommodityId();
		}
		
		return QrCodeUtil.encoderQRCode(url);
	}
}
