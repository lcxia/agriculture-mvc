package cn.agriculture.web.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.agriculture.web.form.QrCodeForm;
import cn.agriculture.web.form.UVO;
import cn.agriculture.web.service.QrCodeService;

@Slf4j
@Controller("QrCodeController")
@RequestMapping("/")
public class QrCodeController {

	@Autowired
	QrCodeService qrCodeService;
	
	@RequestMapping(value = "initQrCode", method = RequestMethod.GET)
	public String initQrCode(Model model, HttpSession session) {
		log.info("分销商自定义价格初始化");
		UVO uvo = (UVO)session.getAttribute("UVO");
		QrCodeForm qrCodeForm = new QrCodeForm();
		qrCodeForm.setDistributorId(uvo.getUserId());
		model.addAttribute("list", qrCodeService.searchQrCodeList(qrCodeForm));
        return "manager/qrCode/qrCodeList";
	}
	
	@RequestMapping(value = "createQrCode", method = RequestMethod.GET)
	public void showImage(HttpServletResponse response, QrCodeForm qrCodeFrom) throws IOException {
		log.info("生成二维码");
		response.setContentType("image/png");
		OutputStream stream = response.getOutputStream();
		try {
			stream.write(qrCodeService.createQrCode(qrCodeFrom));
		} catch (Exception e) {
			log.info("二维码生成失败！");
		} finally {
			stream.flush();
			stream.close();
		}
       
	}
}
